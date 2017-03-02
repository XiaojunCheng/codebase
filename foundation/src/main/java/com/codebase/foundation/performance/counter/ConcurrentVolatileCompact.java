package com.codebase.foundation.performance.counter;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentVolatileCompact implements TestCase {

    public final static class CompactCounter {
        public volatile long value;
    }

    static final CompactCounter[] counter = new CompactCounter[32];

    static {
        for (int i = 0; i < counter.length; ++i) {
            counter[i] = new CompactCounter();
        }
    }

    private final int concurrency;
    private final long loop;
    private final String name;
    private final Thread[] threads;

    final AtomicLong verify = new AtomicLong();

    public ConcurrentVolatileCompact(int concurrency, long loop) {
        this.concurrency = concurrency;
        this.loop = loop;
        this.threads = new Thread[concurrency];
        this.name = "concurrent-compact-" + concurrency + "-" + Constants.name(loop);

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

    public String getName() {
        return this.name;
    }

    public void init() {
        for (int i = 0; i < concurrency; ++i) {
            final int id = i;
            counter[id].value = 0;

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
}
