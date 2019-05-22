package com.codebase.framework.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.codebase.framework.dubbo.demo.DemoService;
import com.codebase.framework.dubbo.demo.ShowDemoDetailParam;
import com.codebase.framework.dubbo.demo.ShowDemoParam;
import com.google.common.collect.Lists;

/**
 * Created by chengxiaojun on 17/2/13.
 */
public class ConsumerByDirectConnect {

    public static void main(String[] args) throws Exception {
        //服务消费方应用名
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("consumer-of-helloworld-app");
        applicationConfig.setOwner("chengxiaojun");

        // 服务消费方模块名
        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.setName("hello-module");

        //注册中心
        /**RegistryConfig registryConfig = new RegistryConfig();
        //registryConfig.setAddress("zookeeper://127.0.0.1:2181");
         */

        //引用服务
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setProtocol("hessian");
        referenceConfig.setGroup("test-rpc");
        referenceConfig.setVersion("1.0");
        //配置服务暴露的ip（本机ip）以及端口进行直连
        referenceConfig.setUrl("dubbo://127.0.0.1:20880/com.codebase.framework.dubbo.demo.DemoService");
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setModule(moduleConfig);
        //referenceConfig.setRegistry(registryConfig);

        /**
         * 获取服务的引用
         */
        DemoService service = referenceConfig.get();
        //
        ShowDemoParam param = new ShowDemoParam();
        param.setDemoId(2000L);
        param.setParams(Lists.newArrayList(new ShowDemoDetailParam("A", 101L), new ShowDemoDetailParam("B", 102L)));
        String result = service.showDemo(param);
        System.out.println(result);
        referenceConfig.destroy();
    }
}
