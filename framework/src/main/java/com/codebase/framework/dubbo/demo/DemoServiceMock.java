package com.codebase.framework.dubbo.demo;

/**
 * Created by chengxiaojun on 17/2/15.
 */
public class DemoServiceMock implements DemoService {
    @Override
    public String sayHello(String name) {
        return "mock";
    }
}
