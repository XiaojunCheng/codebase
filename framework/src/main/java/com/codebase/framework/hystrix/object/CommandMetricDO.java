package com.codebase.framework.hystrix.object;

import lombok.Data;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/15
 */
@Data
public class CommandMetricDO {

    private long currentTime;

    private String commandGroup;
    private String commandName;
    private String threadPoolKey;

    private int totalTimeMean;
    private int executionTimeMean;
    private int currentConcurrentExecutionCount;

    private long totalRequests;
    private long errorCount;
    private int errorPercentage;


}
