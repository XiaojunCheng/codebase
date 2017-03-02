package com.codebase.foundation.performance.counter;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentSync implements TestCase {

    public final static class CompactCounter {
        public volatile long value;
    }

    private final int concurrency;
    private final long loop;
    private final String name;
    private final Thread[] threads;

    final AtomicLong verify = new AtomicLong();

    public ConcurrentSync(int concurrency, long loop) {
        this.concurrency = concurrency;
        this.loop = loop;
        this.threads = new Thread[concurrency];
        this.name = "concurrent-sync-" + concurrency + "-" + Constants.name(loop);
    }

    public String getName() {
        return this.name;
    }

    final CompactCounter counter = new CompactCounter();

    public Object action() throws Exception {
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        verify.addAndGet(counter.value);
        return verify.get();
    }



    public void init() {

        for (int i = 0; i < concurrency; ++i) {
            threads[i] = new Thread() {
                public void run() {

                    for (int i = 0; i < loop; ++i) {
                        synchronized (counter) {
                            counter.value++;
                        }
                    }
                }
            };
        }
    }
}
