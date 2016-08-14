package com.codebase.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

class IpUtil {

    private static String getWindowsIp() throws UnknownHostException {
        return InetAddress.getByName(System.getenv("COMPUTERNAME")).getHostAddress();
    }

    private static String getLinuxOSIp() throws Exception {

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();

            Enumeration<InetAddress> ipAddresses = ni.getInetAddresses();
            while (ipAddresses.hasMoreElements()) {
                InetAddress address = ipAddresses.nextElement();
                if (address.isSiteLocalAddress() //
                        && !address.isLoopbackAddress() // 127.开头的都是loopback地址
                        && address.getHostAddress().indexOf(":") == -1 //
                        ) {
                    return address.getHostAddress();
                }
            }
        }

        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String getIp() {
        try {
            if (OSUtil.isWindowsOS()) {
                return getWindowsIp();
            } else if (OSUtil.isLinuxOS()) {
                return getLinuxOSIp();
            } else {
                return getLinuxOSIp();
            }
        } catch (Exception e) {
            throw new RuntimeException("acquire ip error", e);
        }
    }

}
