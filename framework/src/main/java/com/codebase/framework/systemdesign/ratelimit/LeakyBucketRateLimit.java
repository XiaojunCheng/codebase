package com.codebase.framework.systemdesign.ratelimit;

/**
 * 漏桶模型
 *
 * @author Xiaojun.Cheng
 * @date 2018/12/28
 */
public class LeakyBucketRateLimit implements RateLimit {

    private static final int MILL_ONE_SEC = 1000;

    private volatile int qps = 1;
    private volatile int capacity = qps * MILL_ONE_SEC;
    private volatile long lastVisitTimeInMs = System.currentTimeMillis();
    private volatile long water = 0;

    @Override
    public void setQPS(int qps) {
        this.qps = qps;
        this.capacity = qps * 1000;
    }

    @Override
    public boolean allowThisRequest() {
        long currentTime = System.currentTimeMillis();
        //先漏水
        water = Math.max(0, water - (currentTime - lastVisitTimeInMs) * qps);
        lastVisitTimeInMs = currentTime;
        if (water < capacity) {
            water = water + 1000;
            return true;
        } else {
            return false;
        }
    }
}
