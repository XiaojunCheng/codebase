package com.codebase.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeUtil {

    public static Class<?> getParameterizedClassType(Class<?> clazz) {
        Type genericSuperclass = clazz.getGenericSuperclass();

        while (genericSuperclass != null && !(genericSuperclass instanceof ParameterizedType)) {
            genericSuperclass = ((Class<?>) genericSuperclass).getGenericSuperclass();
        }
        if (genericSuperclass == null) {
            return Object.class;
        }

        ParameterizedType type = (ParameterizedType) genericSuperclass;
        return (Class<?>) type.getActualTypeArguments()[0];
    }

    public static Class<?> getParameterizedInterfaceType(Class<?> clazz) {

        Type[] interfaces = clazz.getGenericInterfaces();
        if (interfaces == null) {
            return Object.class;
        }

        Type interfaceType = interfaces[0];
        if (interfaceType instanceof ParameterizedType) {
            Type[] classTypes = ((ParameterizedType) interfaceType).getActualTypeArguments();
            return (Class<?>) classTypes[0];
        } else {
            return Object.class;
        }
    }

}
