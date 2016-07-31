package com.codebase.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtil.class);

    public static final String IP = getHostAddress();

    private static boolean isWindowsOS(String osName) {
        if (osName == null || osName.isEmpty()) {
            return false;
        }

        osName = osName.toLowerCase().trim();
        if (osName.startsWith("windows")) {
            return true;
        }

        return false;
    }

    private static boolean isLinuxOS(String osName) {
        if (osName == null || osName.isEmpty()) {
            return false;
        }

        osName = osName.toLowerCase().trim();
        if (osName.startsWith("linux")) {
            return true;
        }

        return false;
    }

    private static String getLinuxOSHostAddress() throws Exception {

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements()) {
            //
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> ipAddresses = ni.getInetAddresses();
            LOGGER.info("ni: " + ni.toString());
            //
            while (ipAddresses.hasMoreElements()) {
                //
                InetAddress address = ipAddresses.nextElement();
                LOGGER.info("\taddress: " + address.toString());
                //
                if (address.isSiteLocalAddress() //
                        && !address.isLoopbackAddress() // 127.开头的都是lookback地址
                        && address.getHostAddress().indexOf(":") == -1 //
                ) {
                    LOGGER.info("find address name: " + address.getHostName());
                    return address.getHostAddress();
                }
            }

        }

        return InetAddress.getLocalHost().getHostAddress();
    }

    private static String getHostAddress() {

        String osName = System.getProperty("os.name").toLowerCase().trim();
        LOGGER.info("os name: " + osName);

        try {
            if (isWindowsOS(osName)) {
                return InetAddress.getByName(System.getenv("COMPUTERNAME")).getHostAddress();
            } else if (isLinuxOS(osName)) {
                return getLinuxOSHostAddress();
            } else {
                return getLinuxOSHostAddress();
            }
        } catch (Exception e) {
            throw new RuntimeException("acquire local host ip address error.");
        }
    }
}
