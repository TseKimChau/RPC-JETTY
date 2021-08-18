package com.kimchau.rpc.server.impl;

import com.kimchau.rpc.server.TestInterface;

public class TestClass implements TestInterface {
    @Override
    public String hello() {
        return "hello";
    }
}
