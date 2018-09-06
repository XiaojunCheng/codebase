package com.codebase.framework.asm.core.write;

import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/7
 */
public class RemoveClassMemberClassWriter extends ClassVisitor {

    public RemoveClassMemberClassWriter(int api, ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
    }

    @Override
    public void visitSource(String source, String debug) {
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
    }

    @Override
    public void visitInnerClass(String name, String outerName,
                                String innerName, int access) {
    }
}
