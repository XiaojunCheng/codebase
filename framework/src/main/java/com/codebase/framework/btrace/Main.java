package com.codebase.framework.btrace;

import java.util.Date;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/16
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            print();
            Thread.sleep(5000);
        }
    }

    static void print() {
        System.out.println("@ " + new Date());
    }

}
