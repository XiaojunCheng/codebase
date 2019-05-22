package com.codebase.core.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedClassList {

    private static final Logger LOG = LoggerFactory.getLogger(SharedClassList.class);

    private final Map<String, Class<?>> classMap = new ConcurrentHashMap<>();

    public SharedClassList() {
    }

    public void put(String fullName, Class<?> clazz) {
        classMap.put(fullName, clazz);
        LOG.info("add shared class {}", fullName);
    }

    public Class<?> get(String fullName) {
        return classMap.get(fullName);
    }

    public void clear() {
        classMap.clear();
    }
}
