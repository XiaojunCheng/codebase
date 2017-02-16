package com.codebase.framework.dubbo.demo.provider;

import com.codebase.framework.dubbo.demo.DemoService;

import java.util.Date;

/**
 * Created by chengxiaojun on 17/2/13.
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + ", time: " + new Date();
    }
}
