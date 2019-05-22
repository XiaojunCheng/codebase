package com.codebase.framework.asm.core.read;

import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/6
 */
@Slf4j
public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
        super(Opcodes.ASM4);
    }

    @Override
    public void visit(
            final int version,
            final int access,
            final String name,
            final String signature,
            final String superName,
            final String[] interfaces) {
        log.info("================= > visit");
        log.info("version: {}, access: {}, name: {}, signature: {}", version, access, name, signature);
        log.info("superName: {}, interfaces: {}", superName, interfaces);
        if (cv != null) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }

    @Override
    public void visitSource(final String source, final String debug) {
        log.info("================= > visitSource");
        log.info("source: {}, debug: {}", source, debug);
        if (cv != null) {
            cv.visitSource(source, debug);
        }
    }

    public static void main(String[] args) throws IOException {
        String className = "java.lang.Runnable";

        //方式一: 直接通过类名加载
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader(className);
        cr.accept(cp, 0);

        //方式二: 直接通过InputStream加载
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream classInputStream = cl.getResourceAsStream(className.replace('.', '/') + ".class");
        ClassReader classReader = new ClassReader(classInputStream);
        classReader.accept(cp, 0);
    }

}
