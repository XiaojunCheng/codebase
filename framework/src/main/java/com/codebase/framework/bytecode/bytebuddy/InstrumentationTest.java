package com.codebase.framework.bytecode.bytebuddy;

import com.codebase.framework.asm.core.write.HelloWorldClassWriter;
import lombok.Getter;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collection;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * @author chengxiaojun
 */
public class InstrumentationTest {

    public static void main(String[] args) throws Exception {
        System.out.println(Type.getType(Throwable.class).getDescriptor());
        System.out.println(Type.getType(Throwable.class).getInternalName());
        ASMifier.main(new String[]{T.class.getCanonicalName()});

        Instrumentation instrumentation = ByteBuddyAgent.install();

        ClassFileTransformer cft = new MyTransformer();
        instrumentation.addTransformer(cft, true);
        instrumentation.retransformClasses(T.class);
        instrumentation.removeTransformer(cft);
        {
            T t = new T();
            t.test();
        }
    }

}

class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.equals("com/codebase/framework/bytecode/bytebuddy/T")) {
            return classfileBuffer;
        }

        System.out.println("transform: " + className);
        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(cr, 0);
        ClassVisitor filter = new MyClassVisitor(cw, className);
        TraceClassVisitor cv = new TraceClassVisitor(filter, new PrintWriter(System.out));
        cr.accept(cv, ClassReader.EXPAND_FRAMES);
        HelloWorldClassWriter.writeByteCode2ClassFile(className, cw.toByteArray());
        return cw.toByteArray();
    }
}

class MyClassVisitor extends ClassVisitor {

    private final String className;

    public MyClassVisitor(ClassVisitor cv, final String className) {
        super(ASM4, cv);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(
            final int access,
            final String name,
            final String descriptor,
            final String signature,
            final String[] exceptions) {
        if (cv != null) {
            MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
            return new MyMethodVisitor(mv, access, name, descriptor, className);
        }
        return null;
    }
}

class MyMethodVisitor extends AdviceAdapter {

    private final String className;
    private final String methodName;
    private final Label beginLabel = new Label();
    private final Label endLabel = new Label();

    public MyMethodVisitor(MethodVisitor methodVisitor, final int access,
                           final String name,
                           final String descriptor,
                           final String className) {
        super(ASM4, methodVisitor, access, name, descriptor);
        this.className = className;
        this.methodName = name;
    }

    @Override
    protected void onMethodEnter() {
        mark(beginLabel);
    }

    @Override
    protected void onMethodExit(final int opcode) {
        //为什么异常要进行特殊处理
        if (isThrow(opcode)) {
            return;
        }
    }

    public static boolean isThrow(int opcode) {
        return opcode == ATHROW;
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mark(endLabel);
        visitTryCatchBlock(beginLabel, endLabel, mark(), Type.getType(Throwable.class).getInternalName());
        newInstance(Type.getType(Throwable.class));
        dupX1();
        swap();
        invokeConstructor(Type.getType(Throwable.class), Method.getMethod("void <init> (" + Type.getType(Throwable.class).getClassName() + ")"));
        throwException();
        super.visitMaxs(maxStack, maxLocals);
    }

    /**
     * 用于try-catch的重排序,目的是让tracing的try...catch能在exceptions tables排在前边
     */
    private final Collection<AsmTryCatchBlock> asmTryCatchBlocks = new ArrayList<>();

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        asmTryCatchBlocks.add(new AsmTryCatchBlock(start, end, handler, type));
    }

    @Override
    public void visitEnd() {
        for (AsmTryCatchBlock tcb : asmTryCatchBlocks) {
            super.visitTryCatchBlock(tcb.getStart(), tcb.getEnd(), tcb.getHandler(), tcb.getType());
        }
        super.visitEnd();
    }

    @Override
    public void visitLineNumber(final int line, final Label start) {
        if (mv != null) {
            mv.visitLineNumber(line, start);
            System.out.println("\tat " + className + "." + methodName + "(" + className + ".java:" + line + ")");
        }
    }
}

@Getter
class AsmTryCatchBlock {

    private final Label start;
    private final Label end;
    private final Label handler;
    private final String type;

    public AsmTryCatchBlock(Label start, Label end, Label handler, String type) {
        this.start = start;
        this.end = end;
        this.handler = handler;
        this.type = type;
    }

}


