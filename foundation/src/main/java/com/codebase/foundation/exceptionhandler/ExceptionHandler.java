package com.codebase.foundation.exceptionhandler;

public class ExceptionHandler {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("hello, ex");
            }
        });

        t.start();
    }
}
