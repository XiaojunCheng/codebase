package com.codebase.framework.asm.basic;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/6
 */
public class BasicUsage {

    public static void main(String[] args) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        cl.getResourceAsStream(Runnable.class.getName().replace('.', '/') + ".class");
    }

}
