package com.codebase.foundation.instrumentation;

import java.lang.instrument.Instrumentation;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/3/2.
 */
public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("I'm agent");
        inst.addTransformer(new Transformer());
    }

}
