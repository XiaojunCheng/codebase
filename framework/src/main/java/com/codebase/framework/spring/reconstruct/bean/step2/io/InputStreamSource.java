package com.codebase.framework.spring.reconstruct.bean.step2.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public interface InputStreamSource {

    InputStream getInputStream() throws IOException;

}
