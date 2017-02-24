package com.codebase.core.container;

import com.codebase.core.container.utils.UnzipJar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * - bundle-name/classes
 * - bundle-name/lib/*.jar
 * - bundle-name/lib/*.jar (with inner jars)
 */
public class BundleClassLoader extends URLClassLoader {

    private static final Logger LOG = LoggerFactory.getLogger(BundleClassLoader.class);

    private static final String TMP_PATH = new File("./tmp").getAbsolutePath();

    private final SharedClassList sharedClasses;

    public BundleClassLoader(File workspace, SharedClassList sharedClasses) throws MalformedURLException {
        super(loadJars(workspace));
        this.sharedClasses = sharedClasses;
    }

//    @Override
//    public Class<?> loadClass(String className) throws ClassNotFoundException {
//
//        Class clazz = super.findLoadedClass(className);
//        if (clazz != null) {
//            LOG.info("load {} by call findLoadedClass", className);
//            return clazz;
//        }
//
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        if (classLoader == null) {
//            LOG.warn("SystemClassLoader is null");
//            throw new ClassNotFoundException("SystemClassLoader is null");
//        }
//
//        try {
//            clazz = classLoader.loadClass(className);
//            if (clazz != null) {
//                LOG.info("load {} by call SystemClassLoader.loadClass", className);
//                return clazz;
//            }
//        } catch (ClassNotFoundException e) {
//        }
//
//        clazz = super.findClass(className);
//        if (clazz != null) {
//            LOG.info("load {} by call findClass", className);
//            return clazz;
//        }
//
//        clazz = sharedClasses.get(className);
//        if (clazz != null) {
//            LOG.info("load {} by call sharedClasses.get", className);
//            return clazz;
//        }
//        LOG.warn("not found class {}", className);
//        throw new ClassNotFoundException(className);
//    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        Class<?> clazz;
        try {
            clazz = super.findClass(className);
            if (clazz != null) {
                LOG.info("load {} by call findClass", className);
                return clazz;
            }
        } catch (ClassNotFoundException e) {
            LOG.warn("find class {} error", className);
        }

        clazz = sharedClasses.get(className);
        if (clazz != null) {
            LOG.info("load {} by call sharedClasses.get", className);
            return clazz;
        }
        LOG.warn("not found class {}", className);
        throw new ClassNotFoundException(className);
    }

    private static URL[] loadJars(File workspace) throws MalformedURLException {
        File libDir = new File(workspace.getAbsoluteFile() + "/lib");
        File[] jars = libDir.listFiles(new FilenameFilter() {
            public boolean accept(File file, String name) {
                return name.endsWith(".jar");
            }
        });

        List<URL> urls = new ArrayList<>();
        for (File jar : jars) {
            urls.add(jar.toURI().toURL());
        }

        for (File file : jars) { //add inner jar to class path
            if (UnzipJar.contains(file.getAbsolutePath(), ".jar")) {
                List<File> innerJars = extractInnerJars(workspace.getName(), file.getAbsolutePath());
                for (File jar : innerJars) {
                    urls.add(jar.toURI().toURL());
                }
            }
        }

        String classPath = workspace.getAbsolutePath() + "/classes/";
        File classDir = new File(classPath);
        if (classDir.exists()) {
            urls.add(classDir.toURI().toURL());
        }

        URL[] results = new URL[urls.size()];
        for (int i = 0; i < urls.size(); i++) {
            results[i] = urls.get(i);
        }
        return results;
    }

    private static List<File> extractInnerJars(String name, String jarFile) {
        LOG.info("extract inner jars {}", jarFile);
        String dest = TMP_PATH + File.separator + name;
        try {
            return UnzipJar.unzipJar(dest, jarFile, ".jar");
        } catch (IOException e) {
            LOG.warn("extract jar file {} failed", jarFile);
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
        File bundleDir = new File("bundle/bundleB");
        for (URL u : BundleClassLoader.loadJars(bundleDir)) {
            System.err.println(u);
        }
        BundleClassLoader loader = new BundleClassLoader(bundleDir, null);
        System.out.println(loader.loadClass("com.codebase.core.container.samplea.B"));
        loader.close();
    }
}
