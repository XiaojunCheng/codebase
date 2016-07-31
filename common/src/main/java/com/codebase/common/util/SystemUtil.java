package com.codebase.common.util;

import java.lang.management.ManagementFactory;

public class SystemUtil {

    public static final String IP = IpUtil.getIp();
    public static final int PID = Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);

    public static int ipToInt(String ip) {
        String[] ss = ip.split("\\.");
        if (ss.length != 4) {
            return 0;
        }
        byte[] bytes = new byte[ss.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(ss[i]);
        }
        return ByteUtil.bytesToInt(bytes, 0);
    }

    public static String intToIp(int ip) {
        return bytesToIp(ByteUtil.intToBytes(ip), 0);
    }

    public static String bytesToIp(byte[] data, int pos) {
        return ByteUtil.toUnsignedByte(data[pos]) + "." //
                + ByteUtil.toUnsignedByte(data[pos + 1]) + "." //
                + ByteUtil.toUnsignedByte(data[pos + 2]) + "." //
                + ByteUtil.toUnsignedByte(data[pos + 3]);
    }

    public static String getProperty(String key) {
        return System.getProperty(key);
    }
    //    System.out.println(System.getProperty("os.name")); // 操作系统名称
    //    System.out.println(System.getProperty("os.arch")); // 操作系统架构
    //    System.out.println(System.getProperty("os.version")); // 操作系统版本
    //    System.out.println(System.getProperty("java.home")); // java安装目录
    //    System.out.println(System.getProperty("java.version")); // 
    //    System.out.println(System.getProperty("java.vendor")); // 
    //    System.out.println(System.getProperty("java.vendor.url")); // 
    //    System.out.println(System.getProperty("java.vm.specification.version")); // 虚拟机规范版本
    //    System.out.println(System.getProperty("java.vm.specification.vendor")); // 虚拟机规范供应商
    //    System.out.println(System.getProperty("java.vm.specification.name")); // 虚拟机规范名称
    //    System.out.println(System.getProperty("java.vm.version")); // 虚拟机实现版本
    //    System.out.println(System.getProperty("java.vm.vendor")); // 虚拟机实现供应商
    //    System.out.println(System.getProperty("java.vm.name")); // 虚拟机实现名称
    //    System.out.println(System.getProperty("java.specification.version")); // 运行时环境规范版本
    //    System.out.println(System.getProperty("java.specification.vendor")); // 运行时环境规范供应商
    //    System.out.println(System.getProperty("java.specification.name")); // 运行时环境规范名称
    //    System.out.println(System.getProperty("java.class.version")); // 类格式版本号
    //    System.out.println(System.getProperty("java.class.path")); // 类路径
    //    System.out.println(System.getProperty("java.library.path")); // 加载库时搜索的路径列表
    //    System.out.println(System.getProperty("java.io.tmpdir")); // 默认的临时文件路径
    //    System.out.println(System.getProperty("java.compiler")); // 要使用的 JIT 编译器的名称
    //    System.out.println(System.getProperty("java.ext.dirs")); // 一个或多个扩展目录的路径
    //    System.out.println("文件分隔符（在 UNIX 系统中是“/”）:" + System.getProperty("file.separator")); // 
    //    System.out.println("路径分隔符（在 UNIX 系统中是“:”）:" + System.getProperty("path.separator")); // 
    //    System.out.println("行分隔符（在 UNIX 系统中是“/n”）:" + System.getProperty("line.separator")); // 
    //    System.out.println("用户的账户名称:" + System.getProperty("user.name")); // 
    //    System.out.println("用户的主目录:" + System.getProperty("user.home")); // 
    //    System.out.println("用户的当前工作目录: " + System.getProperty("user.dir")); // 
}
