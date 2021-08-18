package com.kimchau.rpc.codec.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonEncoderTest {

    @Test
    public void encoder() {

        JsonEncoder jsonEncoder = new JsonEncoder();

        TestBean bean = new TestBean();
        bean.setAge(11);
        bean.setName("kimchau");
        byte[] jsonObj = jsonEncoder.encoder(bean);

        assertNotNull(jsonObj);

    }
}