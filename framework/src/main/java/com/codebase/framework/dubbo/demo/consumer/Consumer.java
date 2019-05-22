package com.codebase.framework.dubbo.demo.consumer;

import com.codebase.framework.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by chengxiaojun on 17/2/13.
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo/consumer.xml"});
        context.start();

        //获取远程服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        System.out.println("type: " + demoService.getClass());

        System.out.println("consumer start: " + new Date());

        //执行远程方法
        String hello = demoService.showDemo(null);
        System.out.println("consumer end: " + new Date());

        //显示调用结果
        System.out.println(hello);
        context.destroy();
    }
}
