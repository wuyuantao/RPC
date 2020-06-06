package com.smgeek.gkrpc.client;

import com.smgeec.gkrpc.Peer;
import com.smgeek.gkrpc.client.codec.Decoder;
import com.smgeek.gkrpc.client.codec.Encoder;
import com.smgeek.gkrpc.client.codec.JSONDecoder;
import com.smgeek.gkrpc.client.codec.JSONEncoder;
import com.smgeek.gkrpc.client.transpot.HTTPTransportClient;
import com.smgeek.gkrpc.client.transpot.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass =
            HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass
            =RandomTransportSelector.class;
    private int connectCount=1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1",3000)
    );
}
