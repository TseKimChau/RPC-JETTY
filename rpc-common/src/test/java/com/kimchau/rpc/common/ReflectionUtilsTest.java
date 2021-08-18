package com.kimchau.rpc.common;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void getInstance() {

        try {
            Object instance = ReflectionUtils.newInstance(TestClass.class);
            assertNotNull(instance);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getPublicMethods() {

        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals("c",methods[0].getName());

    }

    @Test
    public void invoke() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Object instance = ReflectionUtils.newInstance(TestClass.class);
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Object res = ReflectionUtils.invoke(instance,methods[0]);
        assertEquals("c", res);

    }
}