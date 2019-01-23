package com.codebase.framework.java.classloader;

import com.sun.javafx.util.Logging;

import java.util.ArrayList;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/10
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("Hello and Bye");
        /**
         * 需要注意的是这里并没有输出引导类加载器，这是由于有些 JDK 的实现对于
         * 父类加载器是引导类加载器的情况，getParent()方法返回 null
         */
        System.out.println("=============>");
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.getClass().getName());
            loader = loader.getParent();
        }

        System.out.println("=============>");
        System.out.println("Classloader of this class:"
                + ClassLoaderTest.class.getClassLoader());

        System.out.println("Classloader of Logging:"
                + Logging.class.getClassLoader());

        System.out.println("Classloader of ArrayList:"
                + ArrayList.class.getClassLoader());

        System.out.println(Thread.currentThread().getContextClassLoader());
    }

}
