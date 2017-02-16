package com.codebase.framework.dubbo.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by chengxiaojun on 17/2/9.
 */
public class CarNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        //遇到car元素的时候交给CarBeanDefinitionParser来解析
        registerBeanDefinitionParser("car", new CarBeanDefinitionParser());
    }
}
