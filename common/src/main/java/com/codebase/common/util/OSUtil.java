package com.codebase.common.util;

public class OSUtil {

    private static String OS = System.getProperty("os.name").toLowerCase().trim();

    public static boolean isWindowsOS() {
        return OS.startsWith("windows");
    }

    public static boolean isLinuxOS() {
        return OS.startsWith("linux");
    }

}
