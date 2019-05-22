package com.codebase.framework.dubbo.demo.provider;

import com.alibaba.dubbo.config.ProtocolConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Xiaojun.Cheng
 * @date 2017/8/30
 */
public class DubboChecker implements ApplicationContextAware {

    private ProtocolConfig protocolConfig;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext.containsBean("dubbo"));
        Object obj = applicationContext.getBean("dubbo");
        System.out.println(obj instanceof ProtocolConfig);
        System.out.println(applicationContext.containsBean("rest"));

        ProtocolConfig p1 = applicationContext.getBean("dubbo", ProtocolConfig.class);
        System.out.println(p1 == null);

        try {
            ProtocolConfig p2 = applicationContext.getBean("rest", ProtocolConfig.class);
            System.out.println(p2 == null);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


}
