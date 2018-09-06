package com.codebase.framework.asm.core.write;

import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.apache.commons.io.FileUtils.writeByteArrayToFile;
import static org.objectweb.asm.Opcodes.*;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/6
 */
@Slf4j
public class HelloWorldClassWriter {

    public static void main(String[] args) {
        String className = "pkg/HelloWorld";

        byte[] classBytes = generateHelloWorldClass(className);

        writeByteCode2ClassFile(className, classBytes);

        useGenerateClass(className, classBytes);
    }

    private static void useGenerateClass(String className, byte[] classBytes) {
        SelfDefinedClassLoader classLoader = new SelfDefinedClassLoader();
        Class clazz = classLoader.defineClass(className.replace('/', '.'), classBytes);
        Method[] methods = clazz.getMethods();
        System.out.println(methods.length);
    }

    private static void writeByteCode2ClassFile(String className, byte[] classBytes) {
        final File classFile = new File("./asm-generated-classes/" + className + ".class");
        final File classPath = new File(classFile.getParent());
        //创建类所在的包路径
        if (!classPath.mkdirs() && !classPath.exists()) {
            log.warn("generate class path:{} failed.", classPath);
            return;
        }
        //将类字节码写入文件
        try {
            writeByteArrayToFile(classFile, classBytes);
        } catch (IOException e) {
            log.warn("dump class:{} to file {} failed.", className, classFile, e);
        }
    }

    static class SelfDefinedClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }

    private static byte[] generateHelloWorldClass(String className) {
        ClassWriter cw = new ClassWriter(0);
        //定义类或者接口信息
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                className, null, "java/lang/Object",
                new String[]{"java/lang/Runnable"});

        //定义字段
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "TAG", "Ljava/lang/String;",
                null, "nx").visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();

        //方法定义
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "sayHello",
                "()V", null, null).visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    /**
     * package pkg;
     * public interface HelloWorld extends java.lang.Runnable {
     *
     *     int LESS = -1;
     *     String TAG = "nx";
     *     int GREATER = 1;
     * 
     *     void sayHello();
     * }
     */

}
