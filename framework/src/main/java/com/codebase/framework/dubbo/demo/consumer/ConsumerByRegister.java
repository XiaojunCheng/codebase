package com.codebase.framework.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.codebase.framework.dubbo.demo.DemoService;
import com.codebase.framework.dubbo.demo.ShowDemoDetailParam;
import com.codebase.framework.dubbo.demo.ShowDemoParam;
import com.google.common.collect.Lists;

/**
 * Created by chengxiaojun on 17/2/13.
 */
public class ConsumerByRegister {

    public static void main(String[] args) throws Exception {
        //服务消费方应用名
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("consumerOfDubbo");
        applicationConfig.setOwner("jack");

        //注册中心
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");

        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setInterface(DemoService.class.getCanonicalName());
        referenceConfig.setTimeout(1000);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setUrl(null);
        referenceConfig.setGeneric(true);

        /**
         * 获取服务的引用
         */
        GenericService service = referenceConfig.get();

        ShowDemoParam param = new ShowDemoParam();
        param.setDemoId(2000L);
        param.setParams(Lists.newArrayList(new ShowDemoDetailParam("A", 101L), new ShowDemoDetailParam("B", 102L)));

        String[] paramTypes = new String[]{"com.codebase.framework.dubbo.demo.ShowDemoParam"};
        Object[] params = new Object[]{JSON.parseObject(JSON.toJSONString(param))};
        Object result = service.$invoke("showDemo", paramTypes, params);
        System.out.println(result);
        referenceConfig.destroy();
    }
}
