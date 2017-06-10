package com.codebase.framework.hystrix.dubbo;

import com.codebase.framework.dubbo.demo.DemoService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/23
 */
public class SayHelloCommand extends HystrixCommand<String> {

    protected SayHelloCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("SayHello"));
    }

    @Override
    protected String run() throws Exception {
        return null;
    }

}
