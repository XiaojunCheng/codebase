package com.codebase.foundation.classloader.util;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codebase.common.util.StringUtil;

/**
 * 一些坑:
 * 1. jar依赖的其他jar必须已被加载
 */
public class ClassLoaderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderUtil.class);

    interface ClassFilter<T> {
        boolean accept(Class<?> clazz);
    }

    private static boolean isClass(String resourceName) {
        return resourceName.endsWith(".class") && !resourceName.contains("$");
    }

    private static boolean isNotClass(String resourceName) {
        return !isClass(resourceName);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<Class<? extends T>> scanJar(String file, ClassFilter<T> filter) throws Exception {

        List<Class<? extends T>> clazzList = new ArrayList<>();

        URLClassLoader classLoader = new URLClassLoader(new URL[] {new File(file).toURI().toURL()}, Thread.currentThread().getContextClassLoader());
        JarFile jarFile = new JarFile(file);
        final Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            final JarEntry entry = entries.nextElement();
            String resourceName = entry.getName();
            if (isNotClass(resourceName)) {
                continue;
            }

            String className = resourceName.replaceAll("/", ".").replace(".class", "");
            try {
                Class<?> clazz = classLoader.loadClass(className);
                if (filter.accept(clazz)) {
                    clazzList.add((Class<? extends T>) clazz);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jarFile.close();
        classLoader.close();
        return clazzList;
    }

    @SuppressWarnings("all")
    public static <T> List<Class<? extends T>> scanPackage(String packageName, ClassFilter<T> filter) throws Exception {

        List<Class<? extends T>> list = new ArrayList<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String resourceName = packageName.replaceAll("\\.", "/");
        URL url = loader.getResource(resourceName);
        URI uri = url.toURI();
        if ("jar".equals(uri.getScheme())) {
            CodeSource source = ClassLoaderUtil.class.getProtectionDomain().getCodeSource();
            ZipInputStream zip = new ZipInputStream(source.getLocation().openStream());
            while (true) {
                ZipEntry e = zip.getNextEntry();
                if (e == null) {
                    break;
                }
                String name = e.getName();
                if (!name.startsWith(resourceName) || !isClass(name)) {
                    continue;
                }
                Class<?> clazz = loader.loadClass(name.replaceAll("/", ".").replace(".class", ""));
                if (filter.accept(clazz)) {
                    list.add((Class<? extends T>) clazz);
                }
            }
        } else {
            File urlFile = new File(url.toURI());
            File[] files = urlFile.listFiles();
            if (files != null) {
                for (File f : files) {
                    scanFile(loader, packageName, f, filter, list);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("all")
    private static <T> void scanFile(ClassLoader loader, String packageName, File file, ClassFilter<T> filter, List<Class<? extends T>> list)
            throws ClassNotFoundException {
        if (file.isFile()) {
            String fileName = file.getName();
            if (isNotClass(fileName)) {
                return;
            }
            String className = fileName.replace(".class", "");
            Class clazz = loader.loadClass(packageName + "." + className);
            if (!filter.accept(clazz)) {
                return;
            }
            LOGGER.warn("add class {}", className);
            list.add(clazz);
        } else {
            File[] files = file.listFiles();
            if (files != null) {
                String subPackageName = StringUtil.isNotEmpty(packageName) ? packageName + "." + file.getName() : file.getName();
                for (File f : files) {
                    scanFile(loader, subPackageName, f, filter, list);
                }
            }
        }
    }

}
