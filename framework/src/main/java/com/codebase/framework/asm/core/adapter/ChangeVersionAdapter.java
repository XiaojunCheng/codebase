package com.codebase.framework.asm.core.adapter;

import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/6
 */
@Slf4j
public class ChangeVersionAdapter extends ClassVisitor {

    public ChangeVersionAdapter(ClassVisitor cv) {
        super(ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        cv.visit(V1_5, access, name, signature, superName, interfaces);
    }

    public static void main(String[] args) throws IOException {
        final String className = "org.objectweb.asm.ClassReader";
        /**
         * cp
         */
        {
            ClassReader cr = new ClassReader(className);
            ClassWriter cw = new ClassWriter(0);
            // cv forwards all events to cw
            ClassVisitor cv = new ClassVisitor(ASM4, cw) {
            };
            cr.accept(cv, 0);
            // b2 represents the same class as b1
            byte[] b2 = cw.toByteArray();
        }

        /**
         * 转换
         */
        {
            long startTime = System.currentTimeMillis();
            ClassReader cr = new ClassReader(className);
            ClassWriter cw = new ClassWriter(0);
            // cv forwards all events to cw
            ChangeVersionAdapter cv = new ChangeVersionAdapter(cw);
            cr.accept(cv, 0);
            // b2 represents the same class as b1
            byte[] b2 = cw.toByteArray();
            long endTime = System.currentTimeMillis();
            log.info("opt elapsed time: {}, length: {}", (endTime - startTime), b2.length);
        }

        /**
         * 转换(优化版)
         */
        {
            long startTime = System.currentTimeMillis();
            ClassReader cr = new ClassReader(className);
            ClassWriter cw = new ClassWriter(cr, 0);
            ChangeVersionAdapter cv = new ChangeVersionAdapter(cw);
            cr.accept(cv, 0);
            // b2 represents the same class as b1
            byte[] b2 = cw.toByteArray();
            long endTime = System.currentTimeMillis();
            log.info("opt elapsed time: {}, length: {}", (endTime - startTime), b2.length);
        }
    }
}
