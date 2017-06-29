package com.codebase.framework.hystrix.command;

import com.codebase.framework.hystrix.MetricsCollector;
import com.netflix.hystrix.*;
import rx.Observable;
import rx.Observer;


/**
 * @author Xiaojun.Cheng
 * @date 2017/5/23
 */
public class SayHelloCommand extends HystrixCommand<String> {

    private static final HystrixCommand.Setter cachedSetter =
            HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ItemLock"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetFieldLock"))
                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().
                            withCoreSize(5).//
                            withMaximumSize(5).//
                            withMaxQueueSize(5).//
                            withAllowMaximumSizeToDivergeFromCoreSize(true))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withExecutionTimeoutInMilliseconds(1000)
                            .withMetricsRollingPercentileWindowBuckets(10)
                            .withMetricsRollingPercentileBucketSize(100)
                            .withMetricsRollingStatisticalWindowInMilliseconds(10000)
                            .withMetricsHealthSnapshotIntervalInMilliseconds(2000)
                            .withCircuitBreakerEnabled(true)
                            .withCircuitBreakerErrorThresholdPercentage(50)
                            .withCircuitBreakerRequestVolumeThreshold(20));

    protected SayHelloCommand() {
        super(cachedSetter);
    }

    @Override
    protected String run() throws Exception {
        System.out.println("hello world!");
        return "jack";
    }

    @Override
    protected String getFallback() {
        return null;
    }

    public static void main(String[] args) throws Exception {

        MetricsCollector collector = new MetricsCollector();
        collector.start();

        SayHelloCommand command = new SayHelloCommand();

        Observable<String> observable = command.observe();

        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });

        observable.subscribe(s -> {
            System.out.println("call: " + s);
        });


        SayHelloObservableCommand observableCommand = new SayHelloObservableCommand();


        Observable<String> observable2 = observableCommand.observe();
//        observable2.subscribe(s -> {
//            System.out.println("call: " + s);
//        });

        String result = observable2.toBlocking().single();
        System.out.println(result);

        Thread.currentThread().join();
    }


}
