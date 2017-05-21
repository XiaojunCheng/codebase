package com.codebase.framework.btrace;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/16
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        final SayHello sayHello = new SayHelloImpl();
        final TaskExecutor executor = new TaskExecutor();
        while (true) {
            executor.run(() -> {
                sayHello.say();
            });
            Thread.sleep(5000);
        }
    }

}
