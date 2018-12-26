package com.codebase.framework.systemdesign.ratelimit;

/**
 * <href>https://www.careercup.com/question?id=5147519440912384</href>
 *
 * @author Xiaojun.Cheng
 * @date 2018/12/26
 */
public interface RateLimit {

    /**
     * Sets the rate, from 1 to 1000000 queries per second
     * @param qps
     */
    void setQPS(int qps);

    /**
     * accept or reject a request, called when request is received
     *
     * @return
     */
    boolean allowThisRequest();
}