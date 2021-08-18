package com.kimchau.rpc.example;

import com.kimchau.rpc.client.RPCClient;

import java.lang.reflect.InvocationTargetException;

public class Client {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RPCClient client = new RPCClient();
        CalcService service = client.getProxy(CalcService.class);

        int res1 = service.add(1,2);
        int res2 = service.minus(3,1);

        System.out.println("res1: " + res1);
        System.out.println("res2: " + res2);
    }

}
