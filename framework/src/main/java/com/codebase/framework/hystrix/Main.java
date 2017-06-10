package com.codebase.framework.hystrix;

import com.google.common.collect.Sets;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.hystrix.*;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.metric.consumer.HystrixDashboardStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/23
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        initMonitor();

        initCommand();

        Thread.currentThread().join();
    }

    static Set<String> intrestingGroup = Sets.newHashSet("test");
    static Set<String> intrestingThreadPool = Sets.newHashSet("test-sayHello");

    static final HystrixCommandProperties.Setter commandPropertiesSetter = HystrixCommandProperties.Setter();

    static {
        commandPropertiesSetter
                .withExecutionTimeoutInMilliseconds(1000)
                .withMetricsRollingStatisticalWindowInMilliseconds(50)
                .withMetricsRollingPercentileWindowBuckets(10)
                .withMetricsRollingPercentileBucketSize(100)
                .withCircuitBreakerErrorThresholdPercentage(50)
                .withMetricsRollingStatisticalWindowBuckets(10)
                .withMetricsRollingStatisticalWindowInMilliseconds(10000)
                .withCircuitBreakerEnabled(true)
                .withCircuitBreakerErrorThresholdPercentage(50)
                .withCircuitBreakerSleepWindowInMilliseconds(5000);
    }

    static HystrixCommand.Setter cachedSetter =
            HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("test"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("sayHello"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("test-sayHello"))
//                    .andThreadPoolPropertiesDefaults(//
//                            HystrixThreadPoolProperties.Setter()
//                                    .withCoreSize(5).withMaximumSize(5)
//                                    .withMaxQueueSize(5).withAllowMaximumSizeToDivergeFromCoreSize(true))
                    .andCommandPropertiesDefaults(commandPropertiesSetter
                    );

    public static void initCommand() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Runnable r = () -> {
            RateLimiter limiter = RateLimiter.create(40.00);
            for (long i = 0; i < Long.MAX_VALUE / 2; i++) {

                if (i % 2000 == 0) {
                    if ((i / 2000) % 2 == 0) {
                        commandPropertiesSetter.withCircuitBreakerSleepWindowInMilliseconds(5 * 1000);
                        LOGGER.info("sleep window change to 5s");
                    } else {
                        commandPropertiesSetter.withCircuitBreakerSleepWindowInMilliseconds(10 * 1000);
                        LOGGER.info("sleep window change to 10s");
                    }
                }

                final long id = i;
                limiter.acquire();
                final SayHelloCommand command = new SayHelloCommand(id);
                executor.execute(() -> {
                    try {
                        String result = command.execute();
                        if (result == null) {
                            LOGGER.error("[request-{}] execute failed", id);
                        }
                    } catch (Exception e) {
                        if (e.getCause() == null) {
                            e.printStackTrace();
                        } else {
                            e.getCause().printStackTrace();
                        }
                    }
                });
            }
        };
        Thread thread = new Thread(r);
        thread.setName("taskThread");
        thread.setDaemon(true);
        thread.start();
    }

    static class SayHelloCommand extends HystrixCommand<String> {

        private Random random = new Random();
        private final long id;
        private volatile long bornTime;
        private volatile long startTime;

        public SayHelloCommand(long id) {
            super(cachedSetter);
            this.id = id;
            bornTime = System.currentTimeMillis();
        }

        @Override
        protected String run() throws Exception {
            startTime = System.currentTimeMillis();
            int i = random.nextInt(2000);
            if (i >= 50 && i <= 60) {
                throw new HystrixBadRequestException("back request " + i);
            }

            Thread.sleep(i);
            long endTime = System.currentTimeMillis();
            return "hello-" + (endTime - startTime);
        }

        @Override
        protected String getFallback() {
            long endTime = System.currentTimeMillis();
            LOGGER.warn("[request-{}] execute fallback, time: {}(ms), bornTime: {}ms", id, (endTime - startTime), (endTime - bornTime));//
            return null;
        }
    }

    public static void initMonitor() {
//        Runnable r = () -> {
//            while (true) {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                LOGGER.info("======================================== metric@" + new Date());
//                HystrixCommandMetrics commandMetrics =
//                        HystrixCommandMetrics.getInstance(HystrixCommandKey.Factory.asKey("sayHello"));
//                if (commandMetrics != null) {
//                    pringCommandMetric(commandMetrics);
//                }
//
//                HystrixThreadPoolMetrics threadPoolMetrics =
//                        HystrixThreadPoolMetrics.getInstance(HystrixThreadPoolKey.Factory.asKey("test-sayHello"));
//                if (threadPoolMetrics != null) {
//                    pringThreadPoolMetric(threadPoolMetrics);
//                }
//            }//            Observable<HystrixDashboardStream.DashboardData> dataObservable =
//                    HystrixDashboardStream.getInstance().observe();
//            dataObservable.subscribe(dashboardData -> {
//
//                LOGGER.info("======================================== metric@" + new Date());
//                dashboardData.getCommandMetrics().
//                        stream().
//                        filter(command -> intrestingGroup.contains(command.getCommandGroup().name())).
//                        forEach(commandMetric -> {
//                            pringCommandMetric(commandMetric);
//                        });
//
//                dashboardData.getThreadPoolMetrics().stream().
//                        filter(command -> intrestingThreadPool.contains(command.getThreadPoolKey().name()))
//                        .forEach(threadPoolMetric -> {
//                            pringThreadPoolMetric(threadPoolMetric);
//                        });
//
//            });
//        };
//        Thread thread = new Thread(r);
//        thread.setName("monitorThread");
//        thread.setDaemon(true);
//        thread.start();


        Observable<HystrixDashboardStream.DashboardData> dataObservable =
                HystrixDashboardStream.getInstance().observe();
        dataObservable.subscribe(dashboardData -> {

            LOGGER.info("======================================== metric@" + new Date());
            dashboardData.getCommandMetrics().
                    forEach((HystrixCommandMetrics commandMetric) -> {
                        HystrixMetricsInitializingBean.processCommandMetric(commandMetric);
                    });

            dashboardData.getThreadPoolMetrics().forEach((HystrixThreadPoolMetrics threadPoolMetric) -> {
                HystrixMetricsInitializingBean.processThreadPoolMetric(threadPoolMetric);
            });
        });
    }


}
