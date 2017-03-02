package com.codebase.foundation.performance.counter;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentCAS implements TestCase {

    private final int concurrency;
    private final long loop;
    private final Thread[] threads;
    private final String name;

    public ConcurrentCAS(int concurrency, long loop) {
        this.concurrency = concurrency;
        this.loop = loop * concurrency;
        this.threads = new Thread[concurrency];
        this.name = "concurrent-cas-" + concurrency + "-" + Constants.name(loop);
    }

    public String getName() {
        return this.name;
    }

    final AtomicLong counter = new AtomicLong();

    public Object action() throws Exception {
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        return counter.get();
    }

    public void init() {
        for (int i = 0; i < concurrency; ++i) {
            threads[i] = new Thread() {
                public void run() {
                    while (counter.incrementAndGet() < loop) {
                        // loop
                    }
                }
            };
        }
    }
}
