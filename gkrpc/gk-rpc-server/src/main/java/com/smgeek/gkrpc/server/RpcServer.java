package com.smgeek.gkrpc.server;

import com.segeec.gkrpc.commom.utils.ReflectionUtils;
import com.smgeec.gkrpc.Request;
import com.smgeec.gkrpc.Response;
import com.smgeek.gkrpc.client.codec.Decoder;
import com.smgeek.gkrpc.client.codec.Encoder;
import com.smgeek.gkrpc.client.transpot.RequestHandler;
import com.smgeek.gkrpc.client.transpot.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config){
        this.config=config;
        this.net= ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);

        this.encoder =ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker =new ServiceInvoker();
    }



    public <T>void register(Class<T> interfaceClass,Object bean){
        serviceManager.register(interfaceClass,bean);
    }
    public void start(){
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }




    private RequestHandler handler =new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream outResp) {
            Response response =new Response();
            try {
                byte[] inBytes = IOUtils.readFully(recive,recive.available());
                Request request =decoder.decode(inBytes,Request.class);
                log.info("get request:{}",request);

                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis,request);
                response.setData(ret);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                response.setCode(1);
                response.setMessage("RpcServer got error:"
                +e.getClass().getName()
                +":"+e.getMessage());
            }finally {

                try {
                    byte[] outBytes =encoder.encode(response);
                    outResp.write(outBytes);

                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }
        }
    };
}
