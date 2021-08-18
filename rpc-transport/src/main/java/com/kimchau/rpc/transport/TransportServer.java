package com.kimchau.rpc.transport;

/**
 * @description 1. 初始化，启动监听
 *              2. 处理请求
 *              3. 关闭监听
 */
public interface TransportServer {

    void init(int port, RequestHandler handler);

    void start() throws Exception;

    void stop();

}
