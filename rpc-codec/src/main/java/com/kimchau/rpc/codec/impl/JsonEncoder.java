package com.kimchau.rpc.codec.impl;

import com.alibaba.fastjson.JSON;
import com.kimchau.rpc.codec.Encoder;

/**
 * @description 基于json的序列化实现
 */
public class JsonEncoder implements Encoder {
    @Override
    public byte[] encoder(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
