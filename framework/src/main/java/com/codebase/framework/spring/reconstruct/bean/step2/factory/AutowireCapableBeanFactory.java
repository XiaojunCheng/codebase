package com.codebase.framework.spring.reconstruct.bean.step2.factory;

import com.codebase.framework.spring.reconstruct.bean.BeanCreateException;
import com.codebase.framework.spring.reconstruct.bean.step2.BeanDefinition;
import com.codebase.framework.spring.reconstruct.bean.step2.BeanReference;
import com.codebase.framework.spring.reconstruct.bean.step2.PropertyValue;
import com.codebase.framework.spring.reconstruct.bean.step2.PropertyValues;

import java.lang.reflect.Field;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) {
        Object bean = createBean(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            Field[] fields = beanDefinition.getBeanClass().getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                if (!propertyValues.contains(name)) {
                    continue;
                }

                PropertyValue propertyValue = propertyValues.getPropertyValue(name);
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getRef());
                }
                field.setAccessible(true);
                field.set(bean, value);
            }
        } catch (IllegalAccessException e) {
            throw new BeanCreateException(beanDefinition.getName(), e);
        }
    }

    private Object createBean(BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getClassName();
        try {
            Class clazz = Class.forName(beanClassName);
            beanDefinition.setBeanClass(clazz);
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new BeanCreateException("class instantiation error: " + beanClassName, e);
        } catch (IllegalAccessException e) {
            throw new BeanCreateException("class illegal access: " + beanClassName, e);
        } catch (ClassNotFoundException e) {
            throw new BeanCreateException("class not found: " + beanClassName, e);
        }
    }
}
