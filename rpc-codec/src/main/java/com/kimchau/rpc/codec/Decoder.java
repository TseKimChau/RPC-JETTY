package com.kimchau.rpc.codec;

public interface Decoder {

    <T> T decoder(byte[] bytes, Class<T> clazz);

}
