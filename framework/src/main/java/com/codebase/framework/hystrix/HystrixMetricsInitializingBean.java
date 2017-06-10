package com.codebase.framework.hystrix;

import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixThreadPoolMetrics;
import com.netflix.hystrix.metric.consumer.HystrixDashboardStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.Date;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/31
 */
public class HystrixMetricsInitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixMetricsInitializingBean.class);

    public void init() throws Exception {
        LOGGER.info("Hystrix Metrics starting...");

        Observable<HystrixDashboardStream.DashboardData> dataObservable =
                HystrixDashboardStream.getInstance().observe();
        dataObservable.subscribe(dashboardData -> {

            LOGGER.info("======================================== metric@" + new Date());
            dashboardData.getCommandMetrics().
                    forEach(commandMetric -> {
                        processCommandMetric(commandMetric);
                    });

            dashboardData.getThreadPoolMetrics()
                    .forEach(threadPoolMetric -> {
                        processThreadPoolMetric(threadPoolMetric);
                    });
        });
    }

    public static void processThreadPoolMetric(HystrixThreadPoolMetrics threadPoolMetric) {
        LOGGER.info("==================== thread pool metric");

        LOGGER.info("thread pool: " + threadPoolMetric.getThreadPoolKey().name());
        LOGGER.info("rolling count executed: " + threadPoolMetric.getRollingCountThreadsExecuted());
        LOGGER.info("rolling count rejected: " + threadPoolMetric.getRollingCountThreadsRejected());
        LOGGER.info("cumulative count rejected: " + threadPoolMetric.getCumulativeCountThreadsExecuted());
        LOGGER.info("cumulative count rejected: " + threadPoolMetric.getCumulativeCountThreadsRejected());
        LOGGER.info("active count: " + threadPoolMetric.getCurrentActiveCount());
        LOGGER.info("completed task count: " + threadPoolMetric.getCurrentCompletedTaskCount());
        LOGGER.info("core pool size: " + threadPoolMetric.getCurrentCompletedTaskCount());
        LOGGER.info("pool size: " + threadPoolMetric.getCurrentCompletedTaskCount());
        LOGGER.info("queue size: " + threadPoolMetric.getCurrentCompletedTaskCount());
        LOGGER.info("task count: " + threadPoolMetric.getCurrentCompletedTaskCount());
    }

    public static void processCommandMetric(HystrixCommandMetrics commandMetric) {
        LOGGER.info("==================== command metric");
        LOGGER.info("command group: " + commandMetric.getCommandGroup());
        LOGGER.info("command: " + commandMetric.getCommandKey().name());
        LOGGER.info("thread pool: " + commandMetric.getThreadPoolKey().name());
        LOGGER.info("total time mean: " + commandMetric.getTotalTimeMean());
        LOGGER.info("execution time mean: " + commandMetric.getExecutionTimeMean());
        LOGGER.info("concurrent execution count: " + commandMetric.getCurrentConcurrentExecutionCount());

        HystrixCommandMetrics.HealthCounts health = commandMetric.getHealthCounts();
        LOGGER.info("totalRequests: " + health.getTotalRequests());
        LOGGER.info("errorCount: " + health.getErrorCount());
        LOGGER.info("errorPercentage: " + health.getErrorPercentage());
    }
}
