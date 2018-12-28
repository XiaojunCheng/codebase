package com.codebase.framework.systemdesign.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author Xiaojun.Cheng
 * @date 2018/12/26
 */
public class RateLimitTest {

    /**
     * server instantiates your object, calls setQPS(1)
     * at at time t, user1 makes a request, allowThisRequest() returns true
     * at time t+0.01 sec, user2 makes a request, allowThisRequest() returns false
     * at at time t+1, user4 makes a request, allowThisRequest() returns true
     * at time t+5 sec, user3 makes a request, allowThisRequest() returns true
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        //RateLimit limit = new CountRateLimit();
        RateLimit limit = new RollingWindowRateLimit(1);
        limit.setQPS(2);

        long startTime = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() + ", " + limit.allowThisRequest());

        //
        Thread.sleep(10);
        System.out.println(System.currentTimeMillis() + ", " + limit.allowThisRequest());

        Thread.sleep(30);
        System.out.println(System.currentTimeMillis() + ", " + limit.allowThisRequest());

        long sleepTime4OneSec = 1000 - (System.currentTimeMillis() - startTime);
        Thread.sleep(sleepTime4OneSec);
        System.out.println(System.currentTimeMillis() + ", " + limit.allowThisRequest());

        long sleepTime4FiveSec = 5000 - (System.currentTimeMillis() - startTime);
        Thread.sleep(sleepTime4FiveSec);
        System.out.println(System.currentTimeMillis() + ", " + limit.allowThisRequest());

        RateLimiter rateLimiter = RateLimiter.create(0.05);

    }

}
