package com.codebase.framework.asm.core.method;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/11
 */
public class AddTimerMethodAdapter extends MethodVisitor {

    private final MethodVisitor mv;
    private final String owner;

    private final Label tryStartLabel = new Label();
    private final Label tryEndLabel = new Label();
    private final Label exceptionCatchLabel = new Label();

    public AddTimerMethodAdapter(MethodVisitor methodVisitor, String owner) {
        super(ASM4, methodVisitor);
        this.mv = methodVisitor;
        this.owner = owner;
    }

    @Override
    public void visitCode() {
        mv.visitCode();

        //try
        mv.visitTryCatchBlock(tryStartLabel, tryEndLabel, exceptionCatchLabel, "java/lang/Exception");
        //tryStart
        mv.visitLabel(tryStartLabel);

        //
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

        //tryEnd
        mv.visitLabel(tryEndLabel);

        //goto return
        Label returnLabel = new Label();
        mv.visitJumpInsn(GOTO, returnLabel);

        //catch exception
        mv.visitLabel(exceptionCatchLabel);
        mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Exception"});
        mv.visitVarInsn(ASTORE, 1);
        //handle exception
        Label exceptionHandleLabel = new Label();
        mv.visitLabel(exceptionHandleLabel);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitInsn(ATHROW);

        //return
        mv.visitLabel(returnLabel);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        mv.visitInsn(RETURN);

        //本地变量重命名label
        Label renameLocalVariableLabel = new Label();
        mv.visitLabel(renameLocalVariableLabel);
        mv.visitLocalVariable("e", "Ljava/lang/Exception;", null, exceptionHandleLabel, returnLabel, 1);
        //mv.visitLocalVariable("this", "L" + owner + ";", null, tryStartLabel, label5, 0);

        mv.visitMaxs(maxStack + 4 + 2, maxLocals + 2);
    }
}
