package com.kimchau.rpc.codec.impl;

import com.alibaba.fastjson.JSON;
import com.kimchau.rpc.codec.Decoder;

public class JsonDecoder implements Decoder {
    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
