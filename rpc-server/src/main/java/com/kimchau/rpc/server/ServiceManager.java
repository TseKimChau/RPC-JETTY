package com.kimchau.rpc.server;

import com.kimchau.rpc.common.ReflectionUtils;
import com.kimchau.rpc.proto.RPCRequest;
import com.kimchau.rpc.proto.ServiceDescription;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description 管理rpc暴露的服务
 */
@Slf4j
public class ServiceManager {

    private Map<ServiceDescription, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for(Method method : methods) {
            ServiceInstance instance = new ServiceInstance(bean, method);
            ServiceDescription serviceDesc = ServiceDescription.from(interfaceClass, method);
            services.put(serviceDesc, instance);
            log.info("register service: {} {}", serviceDesc.getClazz(), serviceDesc.getMethod());
        }
    }

    public ServiceInstance lookup(RPCRequest rpcRequest) {
        ServiceDescription seviceDesc = rpcRequest.getServiceDescription();
        return services.get(seviceDesc);
    }

}
