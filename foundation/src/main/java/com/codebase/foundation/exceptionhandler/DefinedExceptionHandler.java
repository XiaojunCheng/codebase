package com.codebase.foundation.exceptionhandler;

import java.lang.Thread.UncaughtExceptionHandler;

public class DefinedExceptionHandler {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("hello, ex");
            }
        });

        t.setUncaughtExceptionHandler(new Handler());
        t.start();
    }

    static class Handler implements UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("================ before");
            System.out.println(e.getMessage());
            System.out.println("================ after");
        }
    }
}
