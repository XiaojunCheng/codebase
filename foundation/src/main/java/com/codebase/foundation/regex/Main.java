package com.codebase.foundation.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/15
 */
public class Main {

    static final Pattern IP_PATTERN = Pattern.compile("^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(,\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})?$");

    public static void main(String[] args) {
        String ip = "1.2.3.4";
        String ip1 = "1.2.3.4,test";
        String ip2 = "1.2.3.4,1.2.3.4";
        String ip3 = "test";
        String ip4 = "test,1.2.3.4";
        System.out.println(ip + "\t" + isIp(ip));
        System.out.println(ip1 + "\t" + isIp(ip1));
        System.out.println(ip2 + "\t" + isIp(ip2));
        System.out.println(ip3 + "\t" + isIp(ip3));
        System.out.println(ip4 + "\t" + isIp(ip4));

    }


    static boolean isIp(String ip) {
        Matcher matcher = IP_PATTERN.matcher(ip);
        return matcher.matches();
    }

}
