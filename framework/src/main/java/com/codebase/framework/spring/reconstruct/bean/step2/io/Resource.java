package com.codebase.framework.spring.reconstruct.bean.step2.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public interface Resource  {

    InputStream getInputStream() throws IOException;

    URL getURL() throws IOException;
}
