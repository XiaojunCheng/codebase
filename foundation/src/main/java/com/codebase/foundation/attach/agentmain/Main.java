package com.codebase.foundation.attach.agentmain;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.util.Date;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/14
 */
public class Main {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, InterruptedException {

        String pid = "81806";
        String jarFile = "/Users/chengxiaojun/Documents/Workplace/GithubRepository/greys-anatomy/agent/target/classes/instrumentation.jar";
        VirtualMachine vm = VirtualMachine.attach(pid);

        System.out.println("loading..." + new Date());
        vm.loadAgent(jarFile, "abcdelfjalf;");
        System.out.println("load ok" + new Date());

        System.out.println("detaching..." + new Date());
        vm.detach();
        System.out.println("detach ok" + new Date());
    }

}
