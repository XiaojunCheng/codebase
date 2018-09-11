package com.codebase.framework.asm.core.method;

import com.codebase.framework.asm.core.write.HelloWorldClassWriter;
import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/11
 */
public class AddTimerClassAdapter extends ClassVisitor {

    private String owner;
    private boolean isInterface;

    public AddTimerClassAdapter(ClassVisitor cv) {
        super(ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        owner = name;
        isInterface = (access & ACC_INTERFACE) != 0;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
                exceptions);
        if (!isInterface && mv != null && !name.equals("<init>")) {
            mv = new AddTimerMethodAdapter(mv, owner);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        if (!isInterface) {
            FieldVisitor fv = cv.visitField(ACC_PUBLIC + ACC_STATIC, "timer", "J", null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(C.class.getCanonicalName());
        ClassWriter cw = new ClassWriter(cr, 0);
        AddTimerClassAdapter cv = new AddTimerClassAdapter(cw);
        cr.accept(cv, 0);
        // b2 represents the same class as b1
        byte[] b2 = cw.toByteArray();
        HelloWorldClassWriter.writeByteCode2ClassFile(C.class.getName().replace('.', '/'), b2);
    }
}
