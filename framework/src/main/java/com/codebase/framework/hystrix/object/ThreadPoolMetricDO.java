package com.codebase.framework.hystrix.object;

import lombok.Data;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/15
 */
@Data
public class ThreadPoolMetricDO {

    private long currentTime;

    private String threadPoolKey;

    private long rollingCountThreadsExecuted;
    private long rollingCountThreadsRejected;

    private long cumulativeCountThreadsExecuted;
    private long cumulativeCountThreadsRejected;

    private int currentActiveCount;
    private long currentCompletedTaskCount;
    private int currentCorePoolSize;
    private int currentPoolSize;
    private int currentQueueSize;
    private int currentTaskCount;
}
