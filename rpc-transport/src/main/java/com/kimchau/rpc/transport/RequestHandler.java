package com.kimchau.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @description 处理网络请求
 */
@FunctionalInterface
public interface RequestHandler {

    void onRequest(InputStream recive, OutputStream toRespon);

}
