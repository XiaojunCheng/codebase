package com.codebase.fundation.exceptionhandler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefinedThreadPoolExceptionHandler {

    public static void main(String[] args) {

        final ExecutorService taskExecutor = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                printException(this, r, t);
            }
        };

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

    private static void printException(ThreadPoolExecutor executor, Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                    future.get();
            } catch (Exception ce) {
                t = ce;
            }
        }
        if (t != null) {
            System.out.println("before");
            System.out.println(t.getMessage());
            System.out.println("after");
            executor.shutdown();
        }
    }

}
