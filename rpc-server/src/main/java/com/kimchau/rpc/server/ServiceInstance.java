package com.kimchau.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @description 表示一个具体的服务
 */
@Data
@AllArgsConstructor
public class ServiceInstance {

    private Object target;
    private Method method;

}
