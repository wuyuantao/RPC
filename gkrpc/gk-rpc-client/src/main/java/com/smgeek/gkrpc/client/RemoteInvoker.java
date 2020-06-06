package com.smgeek.gkrpc.client;

import com.smgeec.gkrpc.Request;
import com.smgeec.gkrpc.Response;
import com.smgeec.gkrpc.ServiceDescriptor;
import com.smgeek.gkrpc.client.codec.Decoder;
import com.smgeek.gkrpc.client.codec.Encoder;
import com.smgeek.gkrpc.client.transpot.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;
    public RemoteInvoker(Class clazz,
                  Encoder encoder,
                  Decoder decoder,
                  TransportSelector selector){
        this.clazz=clazz;
        this.encoder=encoder;
        this.decoder=decoder;
        this.selector=selector;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        Response resp = invokeRemote(request);
        if (resp == null ||resp.getCode()!=0){
            throw new IllegalStateException("fail to " +
                    "invoke remote :"+resp);
        }

        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client =null;
        Response response= null;

        try {
            client = selector.select();
            byte[] outBytes =encoder.encode(request);
            InputStream recive = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(recive,recive.available());
            response= decoder.decode(inBytes,Response.class);
        } catch (IOException e) {
           log.info(e.getMessage());
           response = new Response();
           response.setCode(1);
           response.setMessage("RpcClient got error:" +
                   e.getClass()
                   +":"+e.getMessage());
        } finally {
            if (client!=null){
                selector.release(client);
            }
        }
        return response;
    }

}
