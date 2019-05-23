package com.codebase.framework.bytecode.bytebuddy;

/**
 * @author chengxiaojun
 * @date 2019-05-22
 */
public class T {

    public String sayHello() {
        System.out.println("hello");
        return "haha";
    }

    public void test() {
        try {
            throw new ClassNotFoundException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
