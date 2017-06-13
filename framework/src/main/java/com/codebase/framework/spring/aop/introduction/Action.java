package com.codebase.framework.spring.aop.introduction;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 */
public class Action {

    public static void before() {
        System.out.println("Before");
    }

    public static void after() {
        System.out.println("After");
    }

}
