package com.codebase.framework.dubbo.demo.provider;

import com.codebase.framework.dubbo.demo.DemoService;
import com.youzan.replay.client.Replay;

import java.util.Date;

/**
 * Created by chengxiaojun on 17/2/13.
 */
public class DemoServiceImpl implements DemoService {

    @Replay
    @Override
    public String sayHello(String name) {
        System.out.println("provider start time: " + new Date());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("provider end time: " + new Date());
        return "Hello " + name + ", time: " + new Date();
    }
}
