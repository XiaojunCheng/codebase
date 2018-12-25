package com.codebase.framework.asm.core.method;

import org.objectweb.asm.commons.Method;
import org.objectweb.asm.util.ASMifier;

import java.io.IOException;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/11
 */
public class ByteCodeGenerator {

    public static void main(String[] args) throws IOException {
        String className = C.class.getCanonicalName();
        ASMifier.main(new String[]{
                className
        });

        System.out.println(Method.getMethod("Class forName(String)"));
    }

}
