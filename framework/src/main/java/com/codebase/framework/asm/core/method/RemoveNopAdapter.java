package com.codebase.framework.asm.core.method;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/11
 */
public class RemoveNopAdapter extends MethodVisitor {

    public RemoveNopAdapter(MethodVisitor methodVisitor) {
        super(ASM4, methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode != NOP) {
            mv.visitInsn(opcode);
        }
    }
}
