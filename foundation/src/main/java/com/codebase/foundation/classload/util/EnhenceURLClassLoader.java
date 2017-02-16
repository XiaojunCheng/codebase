package com.codebase.foundation.classload.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class EnhenceURLClassLoader extends URLClassLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnhenceURLClassLoader.class);

    public EnhenceURLClassLoader() {
        this(new URL[]{});
    }

    /* URL 以'/'结尾的为目录,否则为jar包,未指定其父类加载器为系统类加载器 */
    public EnhenceURLClassLoader(URL[] urls) {
        super(urls);
    }

    public void addURL(String url) {
        URL newUrl = null;
        try {
            newUrl = new URL(url);
        } catch (MalformedURLException e) {
            LOGGER.error("invalid url {}", url, e);
            return;
        }
        addURL(newUrl);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        Class clazz = super.findLoadedClass(name);
        if (clazz != null) {
            LOGGER.info("load {} by call findLoadedClass", name);
            return clazz;
        }

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        if (classLoader == null) {
            LOGGER.warn("SystemClassLoader is null");
            throw new ClassNotFoundException("SystemClassLoader is null");
        }

        try {
            clazz = classLoader.loadClass(name);
            if (clazz != null) {
                LOGGER.info("load {} by call SystemClassLoader.loadClass", name);
                return clazz;
            }
        } catch (ClassNotFoundException e) {
            LOGGER.warn("can not found {} by call SystemClassLoader.loadClass", name, e);
        }

        clazz = super.findClass(name);
        if (clazz != null) {
            LOGGER.info("loader {} by call findClass", name);
            return clazz;
        }

        if (clazz == null) {
            throw new ClassNotFoundException(name + " class not found");
        }
        return clazz;
    }
}

