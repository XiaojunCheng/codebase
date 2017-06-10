package com.codebase.framework.spring.reconstruct.bean.step2.xml;

import com.codebase.framework.spring.reconstruct.bean.step2.io.ClassPathResource;
import com.codebase.framework.spring.reconstruct.bean.step2.io.Resource;
import com.codebase.framework.spring.reconstruct.bean.step2.io.ResourceLoader;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class ClassPathXmlResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        return new ClassPathResource(location);
    }
}
