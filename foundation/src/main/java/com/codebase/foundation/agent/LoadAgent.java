package com.codebase.foundation.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/14
 */
public class LoadAgent {

    //jar cvfm agent.jar META-INF/MANIFEST.MF com/codebase/foundation/agent/LoadAgent.class
    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("args: " + args);
        Class[] classes = inst.getAllLoadedClasses();
        for (Class cls : classes) {
            System.out.println(cls.getName());
        }
    }

}
