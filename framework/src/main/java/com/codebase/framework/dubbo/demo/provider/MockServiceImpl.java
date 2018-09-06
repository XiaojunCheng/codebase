package com.codebase.framework.dubbo.demo.provider;

import com.alibaba.fastjson.JSON;
import com.codebase.framework.dubbo.demo.MockParam;
import com.codebase.framework.dubbo.demo.MockService;

/**
 * @author chengxiaojun
 * @date 17/2/15
 */
public class MockServiceImpl implements MockService {

    @Override
    public String mock(MockParam param) {
        String result = JSON.toJSONString(param);
        System.out.println(result);
        return result;
    }

}
