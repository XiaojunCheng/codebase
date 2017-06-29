package com.codebase.framework.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/15
 */
public class CommandSetter {


    HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SayHello"));


}
