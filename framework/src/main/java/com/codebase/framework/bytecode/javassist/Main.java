package com.codebase.framework.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/27
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.codebase.framework.bytecode.javassist.Clazz");

        CtMethod ctMethod = ctClass.getDeclaredMethod("say");
        ctMethod.insertBefore("System.out.println(\"before\");");

        Class clazz = ctClass.toClass();
        Method method = clazz.getMethod("say");
        Object instance = clazz.newInstance();
        method.invoke(instance);

        System.out.println("====================");

        TestClass c = (TestClass) instance;
        c.say();
    }

}
