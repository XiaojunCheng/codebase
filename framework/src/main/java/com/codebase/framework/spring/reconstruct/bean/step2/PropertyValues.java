package com.codebase.framework.spring.reconstruct.bean.step2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class PropertyValues {

    private final Map<String, PropertyValue> values = new HashMap<>();

    public void add(PropertyValue value) {
        values.put(value.getName(), value);
    }

    public boolean contains(String name) {
        return values.containsKey(name);
    }

    public PropertyValue getPropertyValue(String name) {
        return values.get(name);
    }

}
