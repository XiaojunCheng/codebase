package com.codebase.framework.spring.schema;

import com.codebase.framework.spring.schema.bean.Car;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/2/9.
 */
public class CarBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        //car元素对应Car对象类型
        return Car.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {

        String brand = element.getAttribute("brand");
        String engine = element.getAttribute("engine");
        String hp = element.getAttribute("horsePower");

        //把对应的属性设置到bean中
        if (StringUtils.hasText(brand)) {
            builder.addPropertyValue("brand", brand);
        }

        if (StringUtils.hasText(engine)) {
            builder.addPropertyValue("engine", engine);
        }

        if (StringUtils.hasText(hp)) {
            builder.addPropertyValue("horsePower", hp);
        }
    }
}
