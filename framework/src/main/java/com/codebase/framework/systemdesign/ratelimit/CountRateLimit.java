package com.codebase.framework.systemdesign.ratelimit;

/**
 * 非并发版本
 * 注意: 计数器算法缺点在于不平滑,如果有恶意使用的话前面一段时间就可以消耗掉流控限制
 *
 * @author Xiaojun.Cheng
 * @date 2018/12/26
 */
public class CountRateLimit implements RateLimit {

    private static final long MILL_ONE_SEC = 1000L;

    private volatile int qps = 1;

    private volatile long time = -1;
    private volatile long count = 0;

    @Override
    public void setQPS(int qps) {
        this.qps = qps;
    }

    @Override
    public boolean allowThisRequest() {
        long currentTime = System.currentTimeMillis();
        long result = currentTime - time;
        //第一次访问 或者超过了 1秒
        if (time <= 0 || result >= MILL_ONE_SEC) {
            time = currentTime;
            count = 1;
            return true;
        }

        if (count < qps) {
            count++;
            return true;
        } else {
            return false;
        }
    }
}
