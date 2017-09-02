package com.codebase.foundation.attach.agentmain;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/14
 */
public class AttachMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, InterruptedException {

        String pid = "12281";
        VirtualMachine vm = VirtualMachine.attach(pid);

        System.out.println("loading..." + new Date());
        File file = new File("/Users/koudai213/Documents/Workspace/GithubRepository/codebase/foundation/target/agent.jar");
        System.out.println(file.exists());
        vm.loadAgent(file.getAbsolutePath());
        System.out.println("load ok" + new Date());

        Thread.sleep(10 * 1000);

        System.out.println("detaching..." + new Date());
        vm.detach();
        System.out.println("detach ok" + new Date());
    }

}
