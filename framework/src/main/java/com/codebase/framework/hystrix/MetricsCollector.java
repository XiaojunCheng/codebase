package com.codebase.framework.hystrix;

import com.codebase.framework.hystrix.object.CommandMetricDO;
import com.codebase.framework.hystrix.object.ThreadPoolMetricDO;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixThreadPoolMetrics;
import com.netflix.hystrix.metric.consumer.HystrixDashboardStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/31
 */
public class MetricsCollector {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsCollector.class);

    public void start() throws Exception {
        LOGGER.info("Metrics starting...");

        System.setProperty("hystrix.stream.dashboard.intervalInMilliseconds", "2000");

//        DynamicPropertySupport support = new DynamicPropertySupport() {
//            @Override
//            public String getString(String propName) {
//                return System.getProperty(propName);
//            }
//
//            @Override
//            public void addConfigurationListener(PropertyListener expandedPropertyListener) {
//
//            }
//        };
//
//        DynamicPropertyFactory.initWithConfigurationSource(support);
        Observable<HystrixDashboardStream.DashboardData> dataObservable = HystrixDashboardStream.getInstance().observe();
        dataObservable.subscribe(dashboardData -> {

            System.out.println("begin@" + System.currentTimeMillis());

            dashboardData.getCommandMetrics().
                    forEach(commandMetric -> {
                        processCommandMetric(commandMetric);
                    });

            dashboardData.getThreadPoolMetrics()
                    .forEach(threadPoolMetric -> {
                        processThreadPoolMetric(threadPoolMetric);
                    });

            System.out.println("end@" + System.currentTimeMillis());

        });
    }

    public static void processThreadPoolMetric(HystrixThreadPoolMetrics threadPoolMetric) {

        ThreadPoolMetricDO metricDO = new ThreadPoolMetricDO();
        metricDO.setCurrentTime(System.currentTimeMillis());
        metricDO.setThreadPoolKey(threadPoolMetric.getThreadPoolKey().name());

        metricDO.setRollingCountThreadsExecuted(threadPoolMetric.getRollingCountThreadsExecuted());
        metricDO.setRollingCountThreadsRejected(threadPoolMetric.getRollingCountThreadsRejected());

        metricDO.setCumulativeCountThreadsExecuted(threadPoolMetric.getCumulativeCountThreadsExecuted());
        metricDO.setCumulativeCountThreadsRejected(threadPoolMetric.getCumulativeCountThreadsRejected());

        metricDO.setCurrentActiveCount(threadPoolMetric.getCurrentActiveCount().intValue());
        metricDO.setCurrentCompletedTaskCount(threadPoolMetric.getCurrentCompletedTaskCount().longValue());

        metricDO.setCurrentCorePoolSize(threadPoolMetric.getCurrentCorePoolSize().intValue());
        metricDO.setCurrentPoolSize(threadPoolMetric.getCurrentPoolSize().intValue());

        metricDO.setCurrentQueueSize(threadPoolMetric.getCurrentQueueSize().intValue());
        metricDO.setCurrentTaskCount(threadPoolMetric.getCurrentTaskCount().intValue());

        System.out.println(metricDO);
        //TODO send to MQ or write to storage
    }

    public static void processCommandMetric(HystrixCommandMetrics commandMetric) {

        CommandMetricDO metricDO = new CommandMetricDO();
        metricDO.setCurrentTime(System.currentTimeMillis());
        metricDO.setCommandGroup(commandMetric.getCommandGroup().name());
        metricDO.setCommandName(commandMetric.getCommandKey().name());
        metricDO.setThreadPoolKey(commandMetric.getThreadPoolKey().name());

        metricDO.setTotalTimeMean(commandMetric.getTotalTimeMean());
        metricDO.setExecutionTimeMean(commandMetric.getExecutionTimeMean());
        metricDO.setCurrentConcurrentExecutionCount( commandMetric.getCurrentConcurrentExecutionCount());

        HystrixCommandMetrics.HealthCounts health = commandMetric.getHealthCounts();
        metricDO.setTotalRequests(health.getTotalRequests());
        metricDO.setErrorCount(health.getErrorCount());
        metricDO.setErrorPercentage(health.getErrorPercentage());

        System.out.println(metricDO);
        //TODO send to MQ or write to storage
    }
}
