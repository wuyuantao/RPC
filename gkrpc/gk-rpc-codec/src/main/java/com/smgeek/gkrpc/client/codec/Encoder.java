package com.smgeek.gkrpc.client.codec;

/**
 * 序列化
 */
public interface Encoder {
    byte[] encode(Object obj);
}
