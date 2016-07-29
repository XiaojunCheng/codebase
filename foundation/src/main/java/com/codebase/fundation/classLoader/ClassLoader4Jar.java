package com.codebase.fundation.classLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 加载jar文件中符合条件的类,应用场景有任务调度,用户可实现任务接口,将jar上传至调度平台执行
 */
public class ClassLoader4Jar {



    private static final ClassLoader DEFAULT_CLASS_LOADER = Thread.currentThread().getContextClassLoader();

    @SuppressWarnings("unchecked")
    private static <T> List<Class<? extends T>> loadClassList(String jarFile, Class<T> filterClazz) throws IOException {

        List<Class<? extends T>> clazzList = new ArrayList<>();

        JarFile jarFile = new JarFile(jarFile);
        final Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {

            final JarEntry entry = entries.nextElement();
            String name = entry.getName();
            if (!name.endsWith(".class") || name.contains("$")) {
                continue;
            }

            String normalizedName = name.replaceAll("/", ".").replace(".class", "");
            try {
                Class<?> clazz = DEFAULT_CLASS_LOADER.loadClass(normalizedName);
                if (filterClazz.isAssignableFrom(clazz)) {
                    clazzList.add((Class<? extends T>) clazz);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jarFile.close();

        return clazzList;
    }

    public static void main(String[] args) throws Exception {
        String jarFileName = "test.jar";
        List<Class<? extends Runnable>> runnables = loadClassList(jarFileName, Runnable.class);
        for (Class<? extends Runnable> r : runnables) {
            System.out.println(r.getName());
        }
    }

}
