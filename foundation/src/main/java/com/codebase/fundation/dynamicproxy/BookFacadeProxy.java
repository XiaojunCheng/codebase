package com.codebase.fundation.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookFacadeProxy implements InvocationHandler {

    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    private Object obj;

    BookFacadeProxy(Object obj) {
        this.obj = obj;
    }

    //在invoke方法中，可以添加相应的代码，AOP的基石
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method invoke.");//在AOP中，实现日志记录、验证等与具体业务关联不紧密的方法
        //args方法参数列表
        if (null != args) {
            for (int i = 0; i < args.length; i++)
                System.out.println(args[i]);
        }
        Object result = method.invoke(obj, args);//核心的业务方法
        System.out.println("after method invoke.");//与业务关联不紧密的方法实现
        return result;
    }

    public Object getProxyObject() {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
}
