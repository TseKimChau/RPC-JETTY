package com.kimchau.rpc.example;

import com.kimchau.rpc.server.RPCServer;

public class Server {

    public static void main(String[] args) throws Exception {
        RPCServer server = new RPCServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }

}
