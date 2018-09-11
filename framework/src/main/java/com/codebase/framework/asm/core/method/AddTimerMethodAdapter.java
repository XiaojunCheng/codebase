package com.codebase.framework.asm.core.method;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/11
 */
public class AddTimerMethodAdapter extends MethodVisitor {

    private final MethodVisitor mv;
    private final String owner;

    public AddTimerMethodAdapter(MethodVisitor methodVisitor, String owner) {
        super(ASM4, methodVisitor);
        this.mv = methodVisitor;
        this.owner = owner;
    }

    @Override
    public void visitCode() {
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitInsn(LSUB);
            mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack + 4, maxLocals);
    }
}
