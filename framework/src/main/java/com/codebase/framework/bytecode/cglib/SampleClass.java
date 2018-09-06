package com.codebase.framework.bytecode.cglib;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/29
 */
public class SampleClass {

    public String test(String input) {
        return "Hello world!";
    }

    public static void main(String[] args) throws Exception {
        //test1();

        //test2();

        //test3();

        testBeanGenerator();
    }

    public static void testBeanGenerator() throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean, "Hello cglib!");
        Method getter = myBean.getClass().getMethod("getValue");
        System.out.println(getter.invoke(myBean));
    }

    private static void test3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    return proxy.invokeSuper(obj, args);
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.toString());
        proxy.hashCode(); // Does not throw an exception or result in an endless loop.
    }

    private static void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.toString());
    }

    private static void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib!";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));


        System.out.println(proxy.toString());
        System.out.println(proxy.hashCode());
    }
}
