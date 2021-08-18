package com.kimchau.rpc.codec.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonDecoderTest {

    @Test
    public void decoder() {

        JsonEncoder jsonEncoder = new JsonEncoder();

        TestBean bean = new TestBean();
        bean.setAge(11);
        bean.setName("kimchau");
        byte[] jsonObj = jsonEncoder.encoder(bean);

        JsonDecoder jsonDecoder = new JsonDecoder();
        TestBean bean2 = jsonDecoder.decoder(jsonObj, TestBean.class);

        assertEquals(bean.getAge(), bean2.getAge());
        assertEquals(bean.getName(), bean2.getName());

    }
}