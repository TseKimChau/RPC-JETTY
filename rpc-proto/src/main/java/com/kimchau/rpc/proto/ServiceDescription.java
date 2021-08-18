package com.kimchau.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description 表示服务信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescription {

    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterType;

    public static ServiceDescription from(Class clazz, Method method) {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setClazz(clazz.getName());
        serviceDescription.setMethod(method.getName());
        serviceDescription.setReturnType(method.getReturnType().getName());

        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for(int i=0; i<parameterClasses.length; i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }
        serviceDescription.setParameterType(parameterTypes);
        return serviceDescription;
    }

    @Override
    public String toString() {
        return "clazz= " + clazz +
                ", method= " + method +
                ", returnType= " + returnType +
                ", parameterType= " + Arrays.toString(parameterType);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ServiceDescription that = (ServiceDescription) obj;
        return this.toString().equals(obj.toString());
    }
}
