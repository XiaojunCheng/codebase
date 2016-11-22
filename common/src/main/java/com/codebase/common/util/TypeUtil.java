package com.codebase.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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

    public static Class getPrimitiveType(short value) {
        return short.class;
    }

    public static Class getPrimitiveType(int value) {
        return int.class;
    }

    public static Class getPrimitiveType(long value) {
        return long.class;
    }

    public static Class getPrimitiveType(float value) {
        return float.class;
    }

    public static Class getPrimitiveType(double value) {
        return double.class;
    }

    public static Class getPrimitiveType(boolean value) {
        return boolean.class;
    }

    public static Class getPrimitiveType(byte value) {
        return byte.class;
    }

    public static Class getPrimitiveType(char value) {
        return char.class;
    }

    public static Class getPrimitiveType(Object o) {
        return o.getClass();
    }

    public static void printType(Class type, boolean showIsPrimitiveType) {
        System.out.println(type.getName() + (showIsPrimitiveType ? "->" + type.isPrimitive() : ""));
    }

//    public static Class getPrimitiveType(Class type) {
//        if (!type.isPrimitive()) {
//            if (type.equals(Boolean.class)) {
//                return boolean.class;
//            } else if (type.equals(Integer.class)) {
//                return int.class;
//            } else if (type.equals(Short.class)) {
//                return short.class;
//            } else if (type.equals(Long.class)) {
//                return long.class;
//            } else if (type.equals(Byte.class)) {
//                return byte.class;
//            } else if (type.equals(Float.class)) {
//                return float.class;
//            } else if (type.equals(Double.class)) {
//                return double.class;
//            } else if (type.equals(Character.class)) {
//                return char.class;
//            }
//        }
//        throw new IllegalArgumentException("The type should be a wrapped primitive");
//    }

    public static Method findMain(final Class<?> cls) {
        for (final Method method : cls.getMethods()) {
            int mod = method.getModifiers();
            if (method.getName().equals("main") &&
                    Modifier.isPublic(mod) && Modifier.isStatic(mod) &&
                    "void".equals(method.getReturnType().getName()) &&
                    method.getParameterTypes().length == 1 &&
                    String[].class.equals(method.getParameterTypes()[0])) {
                return method;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] intArray = new Integer[3];
        System.out.println(intArray.getClass().isArray());
        System.out.println(intArray instanceof Integer[]);
        System.out.println(intArray.getClass().getComponentType().getName());

        checkMainMethod(TypeUtil.class);

        checkType();
    }

    private static void checkMainMethod(Class clazz) {
        System.out.println("================== check main method ==================");
        Method method = findMain(clazz);
        if (method != null) {
            System.out.println("find main method in class: " + clazz.getName());
        } else {
            System.out.println("can not found method in class: " + clazz.getName());
        }
    }

    private static void checkType() {
        System.out.println("================== check type ==================");
        printType(getPrimitiveType((short) 3), true);//short
        printType(getPrimitiveType(3), true);//int
        printType(getPrimitiveType(3L), true);//long
        printType(getPrimitiveType((byte) 3), true);//byte
        printType(getPrimitiveType((char) 3), true);//char
        printType(getPrimitiveType(false), true);//boolean
        printType(getPrimitiveType(3.3f), true);//float
        printType(getPrimitiveType(3.3), true);//double

        Short shortI = 3;
        Integer intI = 3;
        Long longI = 3L;
        Byte byteI = 3;
        Character charI = '3';
        Boolean boolI = Boolean.FALSE;
        Float floatI = 3.3f;
        Double doubleI = 3.3;
        printType(getPrimitiveType(shortI), true);
        printType(getPrimitiveType(intI), true);
        printType(getPrimitiveType(longI), true);
        printType(getPrimitiveType(byteI), true);
        printType(getPrimitiveType(charI), true);
        printType(getPrimitiveType(boolI), true);
        printType(getPrimitiveType(floatI), true);
        printType(getPrimitiveType(doubleI), true);
    }

}
