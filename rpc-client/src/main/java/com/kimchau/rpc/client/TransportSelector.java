package com.kimchau.rpc.client;

import com.kimchau.rpc.proto.Peer;
import com.kimchau.rpc.transport.TransportClient;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @description server选择器
 */
public interface TransportSelector {
    /**
     * @description 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    TransportClient select();

    /**
     * @description 释放连接资源
     */
    void release(TransportClient client);

    /**
     * @description 关闭连接资源
     */
    void close();

}
