package com.smgeek.gkrpc.server;

import com.smgeek.gkrpc.client.transpot.HTTPTransportServer;
import com.smgeek.gkrpc.client.codec.Decoder;
import com.smgeek.gkrpc.client.codec.Encoder;
import com.smgeek.gkrpc.client.codec.JSONDecoder;
import com.smgeek.gkrpc.client.codec.JSONEncoder;
import com.smgeek.gkrpc.client.transpot.TransportServer;
import lombok.Data;

/**
 * server配置
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    Class<? extends Encoder > encoderClass =JSONEncoder.class;
    Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port =3000;


}
