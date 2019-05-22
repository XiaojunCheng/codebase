package com.codebase.framework.systemdesign.ratelimit;

import sun.misc.Timer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口限流器
 *
 * @author Xiaojun.Cheng
 * @date 2018/12/26
 */
public class RollingWindowRateLimit implements RateLimit {

    private static final long MILL_ONE_SEC = 1000L;

    private volatile int qps = 1;

    private final Timer timer;
    private final AtomicInteger totalCount = new AtomicInteger(0);
    private final int[] countArray;
    private volatile int index = 0;
    private volatile int bucketQps = 1;

    RollingWindowRateLimit(int bucketNum) {
        if (bucketNum <= 0 || bucketNum > MILL_ONE_SEC || MILL_ONE_SEC % bucketNum != 0) {
            throw new RuntimeException("invalid bucketNum");
        }

        countArray = new int[bucketNum];
        long interval = MILL_ONE_SEC / bucketNum;
        timer = new Timer(timer1 -> {
            //更新状态
            int preIndex = index;
            index = (index + 1) % bucketNum;
            totalCount.addAndGet(-countArray[preIndex]);
            countArray[preIndex] = 0;
        }, interval);
        Thread t = new Thread(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            long waitTime = currentTimeMillis % interval;
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            timer.cont();
        });
        t.start();
    }

    @Override
    public void setQPS(int qps) {
        this.qps = qps;
        this.bucketQps = (qps % bucketQps) == 0 ? (qps / bucketQps) : (qps / bucketQps) + 1;
    }

    @Override
    public boolean allowThisRequest() {
        if (countArray[index] >= bucketQps || totalCount.get() >= qps) {
            return false;
        }

        countArray[index]++;
        totalCount.incrementAndGet();
        return true;
    }
}
