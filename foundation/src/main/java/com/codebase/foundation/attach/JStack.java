package com.codebase.foundation.attach;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/14
 */
public class JStack {

    public static void main(String[] args) {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            System.out.println("pid:" + vmd.id() + ":" + vmd.displayName());
        }
    }

}
