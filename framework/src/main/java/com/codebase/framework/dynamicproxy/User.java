package com.codebase.framework.dynamicproxy;

/**
 * @author Xiaojun.Cheng
 * @date 2018/12/29
 */
public class User {

    public void methodPublic1() {
        System.out.println("methodPublic1");
    }

    public void methodPublic2(String a) {
        System.out.println(a + "========methodPublic2");
    }

    public void defaultMethod1(int b) {
        System.out.println(b + "========defaultMethod1");
    }

    public void defaultMethod2() {
        System.out.println("defaultMethod2");
    }

}
