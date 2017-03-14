package com.codebase.framework.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/2/9.
 */
public class CarNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        //遇到car元素的时候交给CarBeanDefinitionParser来解析
        registerBeanDefinitionParser("car", new CarBeanDefinitionParser());
    }
}
