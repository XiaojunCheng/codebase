package com.codebase.common.util;

public class Test {

    public static void main(String[] args) {
        //
        ParameterizedClassTypeImpl classType = new ParameterizedClassTypeImpl();
        System.out.println(TypeUtil.getParameterizedClassType(classType.getClass()));
        System.out.println(TypeUtil.getParameterizedClassType(Integer.class));
        //
        ParameterizedInterfaceTypeImpl interfaceType = new ParameterizedInterfaceTypeImpl();
        System.out.println(TypeUtil.getParameterizedInterfaceType(interfaceType.getClass()));
        System.out.println(TypeUtil.getParameterizedInterfaceType(Integer.class));
    }

}
