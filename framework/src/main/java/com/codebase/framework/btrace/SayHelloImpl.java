package com.codebase.framework.btrace;

import java.util.Date;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/18
 */
public class SayHelloImpl implements SayHello {

    @Override
    public void say() {
        print();
    }

    protected void print() {
        System.out.println("hello @" + new Date());
    }
}
