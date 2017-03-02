package com.codebase.foundation.performance.counter;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentVolatilePadding implements TestCase {

    public final static class PaddingCounter {
        public long p1, p2, p3, p4, p5, p6, p7;
        public volatile long value;
        public long q1, q2, q3, q4, q5, q6, q7;

        public PaddingCounter() {
            p1 = 0L;
            p2 = 0L;
            p3 = 0L;
            p4 = 0L;
            p5 = 0L;
            p6 = 0L;
            p7 = 0L;
            value = 0L;
            q1 = 0L;
            q2 = 0L;
            q3 = 0L;
            q4 = 0L;
            q5 = 0L;
            q6 = 0L;
            q7 = 0L;
        }
    }

    private final int concurrency;
    private final long loop;
    private final Thread[] threads;
    private final String name;

    public ConcurrentVolatilePadding(int concurrency, long loop) {
        this.concurrency = concurrency;
        this.loop = loop;
        this.threads = new Thread[concurrency];
        this.name = "concurrent-padding-" + concurrency + "-" + Constants.name(loop);

    }

    public String getName() {
        return this.name;
    }

    final PaddingCounter[] counter = new PaddingCounter[32];
    final AtomicLong verify = new AtomicLong();

    public void init() {

        for (int i = 0; i < concurrency; ++i) {
            final int id = i;
            counter[id] = new PaddingCounter();
        }

        for (int i = 0; i < concurrency; ++i) {
            final int id = i;
            threads[i] = new Thread() {
                public void run() {

                    for (int i = 0; i < loop; ++i) {
                        counter[id].value++;
                    }

                    verify.addAndGet(counter[id].value);
                }
            };
        }
    }

    public Object action() throws Exception {
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        return verify.get();
    }
}
