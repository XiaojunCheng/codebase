package com.codebase.framework.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/17
 */
public class GroovyClassLoaderMain {

    public static void main(String[] args) throws Exception {
        loadScript();
    }

    public static void loadScript() throws Exception {
        System.out.println("====================== loadScript ======================");

        URL scriptFile = GroovyClassLoaderMain.class.getResource("/groovyscript/TestGroovy.groovy");
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        Class groovyClass = classLoader.parseClass(new GroovyCodeSource(scriptFile));

        GroovyObject instance = (GroovyObject) groovyClass.newInstance();

        Date date = new Date();
        System.out.println(date);

        Long time = (Long) instance.invokeMethod("getTime", date);
        System.out.println(time);

        Date date2 = (Date) instance.invokeMethod("getDate", time);
        System.out.println(date2);
        System.out.println(date2.getTime());

        System.out.println();
    }

}
