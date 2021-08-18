package com.kimchau.rpc.server;

import com.kimchau.rpc.common.ReflectionUtils;
import com.kimchau.rpc.proto.RPCRequest;

import java.lang.reflect.InvocationTargetException;

/**
 * @description 方法调用
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance instance, RPCRequest request) throws InvocationTargetException, IllegalAccessException {
        return ReflectionUtils.invoke(instance.getTarget(), instance.getMethod(), request.getParameters());
    }

}
