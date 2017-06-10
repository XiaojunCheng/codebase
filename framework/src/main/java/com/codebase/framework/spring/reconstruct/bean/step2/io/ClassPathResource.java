package com.codebase.framework.spring.reconstruct.bean.step2.io;

import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
@ToString
public class ClassPathResource implements Resource {

    private final String location;

    public ClassPathResource(String location) {
        this.location = location;
    }

    @Override
    public boolean exists() {
        return (resolveURL() != null);
    }

    @Override
    public URL getURL() throws IOException {
        return resolveURL();
    }

    private URL resolveURL() {
        return ClassLoader.getSystemResource(location);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader.getResourceAsStream(location);
    }

}
