package com.codebase.foundation.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.codebase.foundation.dynamicproxy.service.StoreManager;

public class ManagerProxy implements InvocationHandler {

    private final StoreManager manager;

    public ManagerProxy(final StoreManager manager) {
        this.manager = manager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("start to call");
        Object obj = method.invoke(manager, args);
        System.out.println(method.getName() + "cost time is :" + (System.currentTimeMillis() - start));

        return obj;
    }

    public static void main(String[] args) {

        ManagerProxy proxy = new ManagerProxy(new Manager());

        StoreManager manager = (StoreManager) Proxy.newProxyInstance(StoreManager.class.getClassLoader(), new Class[] {StoreManager.class}, proxy);

        manager.list();

        Class<?> c = Proxy.getProxyClass(StoreManager.class.getClassLoader(), new Class[] {StoreManager.class});
        System.out.println(Proxy.isProxyClass(c));
        System.out.println(c.getClass().getName());
        c.hashCode();
    }

}
