package com.codebase.framework.asm.basic.clazz;

import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chengxiaojun
 */
@Slf4j
public class ClassVisitorTest extends ClassVisitor {

    private final AtomicLong SEQ = new AtomicLong(0);

    public ClassVisitorTest(int api) {
        super(api);
    }

    @Override
    public void visit(
            final int version,
            final int access,
            final String name,
            final String signature,
            final String superName,
            final String[] interfaces) {
        log.info("{}. visit", SEQ.incrementAndGet());
        log.info("\t version: {}, access: {}, name: {}, signature: {}", version, access, name, signature);
        log.info("\t superName: {}, interfaces: {}", superName, interfaces);
        if (cv != null) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }

    @Override
    public void visitSource(final String source, final String debug) {
        log.info("{}. visitSource", SEQ.incrementAndGet());
        log.info("\t source: {}, debug: {}", source, debug);
        if (cv != null) {
            cv.visitSource(source, debug);
        }
    }

    @Override
    public void visitInnerClass(String name, String outerName,
                                String innerName, int access) {
        log.info("{}. visitInnerClass", SEQ.incrementAndGet());
        log.info("\t name: {}, outerName: {}, innerName: {}, access: {}", name, outerName, innerName, access);
        if (cv != null) {
            cv.visitInnerClass(name, outerName, innerName, access);
        }
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        log.info("{}. visitField", SEQ.incrementAndGet());
        log.info("\t access: {}, name: {}, desc: {}, signature: {}, value: {}", access, name, desc, signature, value);
        if (cv != null) {
            return cv.visitField(access, name, desc, signature, value);
        }
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        log.info("{}. visitMethod", SEQ.incrementAndGet());
        log.info("\t access: {}, name: {}, desc: {}, signature: {}, exceptions: {}", access, name, desc, signature, exceptions);
        if (cv != null) {
            return cv.visitMethod(access, name, desc, signature, exceptions);
        }
        return null;
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        log.info("{}. visitOuterClass", SEQ.incrementAndGet());
        log.info("\t owner: {}, name: {}, desc: {}", owner, name, desc);
        if (cv != null) {
            cv.visitOuterClass(owner, name, desc);
        }
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        log.info("{}. visitAnnotation", SEQ.incrementAndGet());
        log.info("\t desc: {}, visible: {}", desc, visible);
        if (cv != null) {
            return cv.visitAnnotation(desc, visible);
        }
        return null;
    }

    @Override
    public void visitAttribute(Attribute attr) {
        log.info("{}. visitAnnotation", SEQ.incrementAndGet());
        log.info("\t attr: {}", attr);
        if (cv != null) {
            cv.visitAttribute(attr);
        }
    }

    @Override
    public void visitEnd() {
        log.info("{}. visitEnd", SEQ.incrementAndGet());
        if (cv != null) {
            cv.visitEnd();
        }
    }

    public static void main(String[] args) throws IOException {
        String className = Clazz.class.getCanonicalName();
        ClassVisitorTest cp = new ClassVisitorTest(Opcodes.ASM4);
        ClassReader cr = new ClassReader(className);
        cr.accept(cp, 0);
    }

}
