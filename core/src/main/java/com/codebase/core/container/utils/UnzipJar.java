package com.codebase.core.container.utils;

import java.io.*;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class UnzipJar {

    private static final int DEFAULT_BUFFER_SIZE = 4 * 1024;

    //unzipJar("../tmp/jcm", "../bundle/jcm/lib/jcm.server-0.1.0.jar", ".jar");
    public static List<File> unzipJar(String destDir, String jarPath, String suffix) throws IOException {

        JarFile jarFile = new JarFile(jarPath);
        List<File> candidates = new LinkedList<>();
        for (Enumeration<JarEntry> enums = jarFile.entries(); enums.hasMoreElements(); ) {

            JarEntry jarEntry = enums.nextElement();
            String destFileName = destDir + File.separator + jarEntry.getName();
            File destFile = new File(destFileName);

            if (!jarEntry.isDirectory() && jarEntry.getName().endsWith(suffix)) {

                destFile.getParentFile().mkdirs();

                InputStream is = jarFile.getInputStream(jarEntry);
                FileOutputStream fos = new FileOutputStream(destFile);
                try {
                    copyStream(is, fos);
                    candidates.add(destFile);
                } finally {
                    fos.close();
                    is.close();
                }
            }
        }
        jarFile.close();
        return candidates;
    }

    public static boolean contains(String jarPath, String suffix) {
        JarFile jarFile;
        boolean ret = false;
        try {
            jarFile = new JarFile(jarPath);
            for (Enumeration<JarEntry> enums = jarFile.entries(); enums.hasMoreElements(); ) {
                JarEntry entry = enums.nextElement();
                if (entry.getName().endsWith(suffix)) {
                    ret = true;
                    break;
                }
            }
            jarFile.close();
        } catch (IOException e) {
            return false;
        }
        return ret;
    }

    private static void copyStream(InputStream is, OutputStream out) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int n;
        while (-1 != (n = is.read(buffer))) {
            out.write(buffer, 0, n);
        }
    }
}
