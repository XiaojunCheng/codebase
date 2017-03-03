package com.codebase.foundation.instrumentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/3/2.
 */
public class Transformer implements ClassFileTransformer {

    private static final String CLASS_NAME = "TransformerClass";

    public static final String TRANSFORMER_CLASS_FILE = CLASS_NAME + ".class.2";

    public static byte[] getBytesFromFile(String fileName) {
        File file = new File(fileName);
        try (InputStream is = new FileInputStream(file)) {// precondition

            long length = file.length();
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            System.out.println("load ok " + file.getAbsolutePath());
            return bytes;
        } catch (Exception e) {
            System.out.println("error occurs in _ClassTransformer!" + e.getClass().getName());
            return null;
        }
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer
    ) throws IllegalClassFormatException {
        if (!className.equals(CLASS_NAME)) {
            return null;
        }

        System.out.println(TRANSFORMER_CLASS_FILE);
        return getBytesFromFile(TRANSFORMER_CLASS_FILE);
    }
}
