package com.codebase.foundation.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Java Management Extensions, introduced in j2se 5.0 release
 */
public class Main {

    private static final int DEFAULT_NO_THREADS = 10;
    private static final String DEFAULT_SCHEMA = "default";

    public static void main(String[] args) throws Exception {

        //Get the MBean server
        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        //register the MBean
        SystemConfig mBean = new SystemConfig(DEFAULT_NO_THREADS, DEFAULT_SCHEMA);
        ObjectName name = new ObjectName(SystemConfig.class.getPackage().getName() + ":type=" + SystemConfig.class.getSimpleName());
        mbeanServer.registerMBean(mBean, name);
        do {
            Thread.sleep(3000);
        } while (mBean.getThreadCount() != 0);

    }
}
