package com.kimchau.rpc.transport;

import com.kimchau.rpc.proto.Peer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * @description 1. 创建连接
 *              2. 传输数据，并等待响应
 *              3. 关闭连接
 */
public interface TransportClient {

    void connect(Peer peer);

    InputStream write(InputStream data) throws IOException;

    void close();

}
