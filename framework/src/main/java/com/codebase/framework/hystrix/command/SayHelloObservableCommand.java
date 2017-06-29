package com.codebase.framework.hystrix.command;

import com.netflix.hystrix.*;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/15
 */
public class SayHelloObservableCommand extends HystrixObservableCommand<String> {

    protected SayHelloObservableCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ItemLock"));
    }

    @Override
    protected Observable<String> construct() {
        throw new RuntimeException();
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(null);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
