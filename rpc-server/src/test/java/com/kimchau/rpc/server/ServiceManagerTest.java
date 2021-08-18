package com.kimchau.rpc.server;

import com.kimchau.rpc.common.ReflectionUtils;
import com.kimchau.rpc.proto.RPCRequest;
import com.kimchau.rpc.proto.ServiceDescription;
import com.kimchau.rpc.server.impl.TestClass;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    private ServiceManager sm;

    @Before
    public void init() {
        sm = new ServiceManager();
        TestClass bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void register() {
        TestClass bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescription serviceDescription = ServiceDescription.from(TestInterface.class, method);
        RPCRequest request = new RPCRequest();
        request.setServiceDescription(serviceDescription);
        ServiceInstance instance = sm.lookup(request);
        assertNotNull(instance);
    }
}