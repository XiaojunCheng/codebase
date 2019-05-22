package com.codebase.framework.asm.core.write;

import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;

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

        printHumanReadableText(classBytes);

        useGenerateClass(className, classBytes);
    }

    public static void printHumanReadableText(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassWriter cw = new ClassWriter(cr, 0);
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        CheckClassAdapter ccv = new CheckClassAdapter(cv);
        cr.accept(ccv, 0);
    }

    public static void useGenerateClass(String className, byte[] classBytes) {
        SelfDefinedClassLoader classLoader = new SelfDefinedClassLoader();
        Class clazz = classLoader.defineClass(className.replace('/', '.'), classBytes);
        Method[] methods = clazz.getMethods();
        System.out.println(methods.length);
    }

    public static void writeByteCode2ClassFile(String className, byte[] classBytes) {
        final File classFile = new File("./asm-generated-classes/" + className + ".class");
        final File classPath = new File(classFile.getParent());
        //创建类所在的包路径
        if (!classPath.mkdirs() && !classPath.exists()) {
            log.warn("generate class path:{} failed.", classPath);
            return;
        }
        //将类字节码写入文件
        try {
            FileUtils.writeByteArrayToFile(classFile, classBytes);
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
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", Type.getType(int.class).getDescriptor(),
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "TAG", Type.getType(String.class).getDescriptor(),
                null, "nx").visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", Type.getType(int.class).getDescriptor(),
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
