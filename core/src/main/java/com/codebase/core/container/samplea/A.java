package com.codebase.core.container.samplea;


public class A {

    static {
        System.out.println("====" + A.class.getSimpleName() + "====");
        System.out.println(A.class.getClassLoader());
        Base base = new Base();
        base.print();
        Export e = new Export();
        e.print();
        System.out.println("====" + A.class.getSimpleName() + "====");
    }
}
