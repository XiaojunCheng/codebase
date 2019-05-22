package com.codebase.framework.dubbo.demo.provider;

import com.alibaba.fastjson.JSON;
import com.codebase.framework.dubbo.demo.ShowDemoParam;
import com.codebase.framework.dubbo.demo.DemoService;

/**
 * @author chengxiaojun
 * @date 17/2/13
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String showDemo(ShowDemoParam param) {
        String result = JSON.toJSONString(param);
        System.out.println(result);
        return result;
    }
}
