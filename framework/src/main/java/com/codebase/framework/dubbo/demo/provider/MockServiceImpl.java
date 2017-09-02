package com.codebase.framework.dubbo.demo.provider;

import com.codebase.framework.dubbo.demo.MockService;

/**
 * Created by chengxiaojun on 17/2/15.
 */
public class MockServiceImpl implements MockService {

    @Override
    public String mock(String name) {
        return "mock-" + name;
    }

}
