package com.codebase.foundation.attach.premain;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/3/2.
 */
public class Transformer implements ClassFileTransformer {

    private static final String PACKAGE_NAME = Transformer.class.getPackage().getName().replace(".", "/");

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer
    ) throws IllegalClassFormatException {

        if (className.startsWith(PACKAGE_NAME)) {
            System.out.println("className: " + className);
        }

        return null;
    }
}
