package com.kimchau.rpc.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 反射工具类
 */
public class ReflectionUtils {

    /**
     * @description 根据class创建对象
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

            return clazz.getDeclaredConstructor().newInstance();

    }

    /**
     * @description 返回某个类的所有公共方法
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> publicMethods =  new ArrayList<>();
        for(Method method : methods) {
            if(Modifier.isPublic(method.getModifiers())) {
                publicMethods.add(method);
            }
        }
        return publicMethods.toArray(new Method[0]);
    }

    /**
     * @description 调用某一对象的某个方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object obj, Method method, Object... args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(obj, args);
    }

}
