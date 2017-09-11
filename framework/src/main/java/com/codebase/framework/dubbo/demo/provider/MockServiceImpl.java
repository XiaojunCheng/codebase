package com.codebase.framework.dubbo.demo.provider;

import com.codebase.framework.dubbo.demo.MockService;
import com.youzan.replay.client.Replay;

/**
 * Created by chengxiaojun on 17/2/15.
 */
public class MockServiceImpl implements MockService {

    @Replay
    @Override
    public String mock(String name) {
        return "mock-" + name;
    }

}
