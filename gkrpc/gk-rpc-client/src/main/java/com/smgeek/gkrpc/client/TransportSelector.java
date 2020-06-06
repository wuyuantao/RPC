package com.smgeek.gkrpc.client;

import com.smgeec.gkrpc.Peer;
import com.smgeek.gkrpc.client.transpot.TransportClient;

import java.util.List;

public interface TransportSelector {
    /**
     *
     * @param peers 可连接的server端点信息
     * @param count client与server建立的连接数量
     * @param clazz client实现class
     */
    void init(List<Peer> peers,
              int count,
              Class<? extends TransportClient >clazz);
    /**
     * 选择一个transport与server做交互
     * @return
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release (TransportClient client);

    void close();
}
