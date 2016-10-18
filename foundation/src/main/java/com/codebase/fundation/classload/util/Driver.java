package com.codebase.fundation.classload.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Driver {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packageName = "org";
        String resourceName = packageName.replaceAll("\\.", "/");

        Enumeration<URL> urls = loader.getResources(resourceName);

        while(urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if(url.getProtocol().equals("jar")) {
                System.out.println(url);
                System.out.println(url.toURI().getScheme());
                String[] infoArray = URLDecoder.decode(url.getPath()).split("!");
                String jarFilePath = infoArray[0].substring(infoArray[0].indexOf("/"));
                String packagePath = infoArray[1].substring(1);
                System.out.println(jarFilePath);
                System.out.println(packagePath);
                JarFile jarFile = new JarFile(jarFilePath);
                final Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    final JarEntry entry = entries.nextElement();
                    System.out.println(entry.getName());
                }
            }
        }
    }
}
