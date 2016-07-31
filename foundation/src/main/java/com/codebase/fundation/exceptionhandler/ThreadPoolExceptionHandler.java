package com.codebase.fundation.exceptionhandler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolExceptionHandler {

    public static void main(String[] args) {

        ExecutorService taskExecutor = Executors.newFixedThreadPool(4);

        Future<String> t = taskExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new RuntimeException("hehe,ex!");
            }
        });

        try {
            t.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
