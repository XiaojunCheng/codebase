package com.codebase.framework.dubbo.demo.provider;

import com.alibaba.dubbo.config.*;
import com.codebase.framework.dubbo.demo.DemoService;

/**
 * Created by chengxiaojun on 17/2/15.
 */
public class Provider2 {

    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("hello-world-app");
        applicationConfig.setOwner("chengxiaojun");

        // <!-- 服务提供方模块名 -->
        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.setName("hello-module");

        // <!-- 注册中心 -->
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");

        // <!-- dubbo服务端口 -->
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20168);

        // <!-- 暴露服务 -->
        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface("com.codebase.framework.dubbo.demo.DemoService");
        serviceConfig.setRef(new DemoServiceImpl());

        // 配置服务的group以及版本号
        // serviceConfig.setGroup("test-rpc");
        serviceConfig.setVersion("1.0");

        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setModule(moduleConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);

        serviceConfig.export();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
