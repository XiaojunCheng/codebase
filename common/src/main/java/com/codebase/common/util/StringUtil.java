package com.codebase.common.util;

public class StringUtil {

    public static final String EMPTY = "";

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 字节数组转化成16进制形式
     */
    public static String bytes2String(byte[] src) {

        StringBuilder sb = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转化成字节数组
     */
    public static byte[] string2Bytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    
}
