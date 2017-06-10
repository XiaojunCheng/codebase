package com.codebase.framework.spring.reconstruct.step2;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) {
        Object bean = createBean(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        Field[] fields = beanDefinition.getBeanClass().getDeclaredFields();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            String name = field.getName();
            if (Modifier.isStatic(modifiers) || !propertyValues.contains(name)) {
                continue;
            }

            field.setAccessible(true);
            try {
                field.set(bean, propertyValues.getPropertyValue(name).getValue());
            } catch (IllegalAccessException e) {
                throw new BeanInitialException("field illegal access: " + name, e);
            }
        }
    }

    private Object createBean(BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getClassName();
        try {
            Class clazz = Class.forName(beanClassName);
            beanDefinition.setBeanClass(clazz);
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new BeanInitialException("class instantiation error: " + beanClassName, e);
        } catch (IllegalAccessException e) {
            throw new BeanInitialException("class illegal access: " + beanClassName, e);
        } catch (ClassNotFoundException e) {
            throw new BeanInitialException("class not found: " + beanClassName, e);
        }
    }
}
