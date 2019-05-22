package com.codebase.core.container.samplea;

import com.codebase.core.container.BundleContext;
import com.codebase.core.container.BundleInitializer;

public class B implements BundleInitializer {

    static {
        System.out.println("====" + B.class.getSimpleName() + "====");
        System.out.println(B.class.getClassLoader());
        Base base = new Base();
        base.print();
        Export e = new Export();
        e.print();
        System.out.println("====" + B.class.getSimpleName() + "====");
    }

    @Override
    public void start(BundleContext context) {
        System.out.println("B start: " + context.getName());
        new A();
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("B stop: " + context.getName());
    }
}
