package com.codebase.common.util;

import java.io.IOException;

public class ShellTest {

    public static void main(String[] args) throws IOException {
        System.out.println(ShellUtil.execCommand(new String[]{"ls"}));
    }

}
