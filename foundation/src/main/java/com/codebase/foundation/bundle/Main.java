package com.codebase.foundation.bundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by chengxiaojun on 17/2/17.
 */
public class Main {

    public static void main(String[] args) {
        final String LOCAL_STRING_FILE = "com.codebase.foundation.bundle.LocalStrings";

        ResourceBundle lStrings = ResourceBundle.getBundle(LOCAL_STRING_FILE, new Locale("en", "US"));
        System.out.println(lStrings.getString("error.spaceIsFull"));
        System.out.println(lStrings.getString("error.noPermission"));

        ResourceBundle lStrings2 = ResourceBundle.getBundle(LOCAL_STRING_FILE);
        System.out.println(lStrings2.getString("error.spaceIsFull"));
        System.out.println(lStrings2.getString("error.noPermission"));
    }
}
