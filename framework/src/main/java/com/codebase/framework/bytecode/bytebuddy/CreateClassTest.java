package com.codebase.framework.bytecode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

public class CreateClassTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("example.Type")
                .make();
        Object obj = dynamicType.load(Thread.currentThread().getContextClassLoader()).getLoaded().newInstance();
        System.out.println(obj.getClass().getName());

    }

}
