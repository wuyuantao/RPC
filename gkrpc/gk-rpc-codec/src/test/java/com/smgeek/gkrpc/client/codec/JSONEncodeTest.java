package com.smgeek.gkrpc.client.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONEncodeTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setName("wyt");
        bean.setAge(24);

        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}