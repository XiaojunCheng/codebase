package com.codebase.framework.asm.core.util;

/**
 * @author chengxiaojun
 * @date 2019-05-22
 */
public class SelfDefinedClassLoader extends ClassLoader {

    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }

    @Override
    public synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        try {
            Class<?> aClass = findClass(name);
            if (resolve) {
                resolveClass(aClass);
            }
            return aClass;
        } catch (Exception e) {
            return super.loadClass(name, resolve);
        }
    }

}
