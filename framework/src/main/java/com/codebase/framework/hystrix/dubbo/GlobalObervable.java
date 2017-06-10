package com.codebase.framework.hystrix.dubbo;

import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixThreadPoolMetrics;
import com.netflix.hystrix.metric.consumer.HystrixDashboardStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/23
 */
public class GlobalObervable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalObervable.class);

    public void init() {
        try {
            //subscribe message from stream
            Observable<HystrixDashboardStream.DashboardData> dataObservable = HystrixDashboardStream.getInstance().observe();
            dataObservable.subscribe(dashboardData -> {
                buildData(dashboardData);
            });
        } catch (Exception e) {
            LOGGER.warn("[event-observable] process dashboard data error", e);
        }
    }

    private void buildData(HystrixDashboardStream.DashboardData dashboardData) {
        dashboardData.getCommandMetrics().forEach(commandMetrics -> {
            buildCommandMetrics(commandMetrics);
        });

        dashboardData.getThreadPoolMetrics().forEach(threadPoolMetrics -> {
            buildThreadPoolMetrics(threadPoolMetrics);
        });
    }

    /**
     * 组装ThreadPoolMetrics 数据
     */
    private void buildThreadPoolMetrics(HystrixThreadPoolMetrics threadPoolMetrics) {
    }

    /**
     * 组装CommandMetrics 数据
     */
    private void buildCommandMetrics(HystrixCommandMetrics commandMetrics) {
    }

}
