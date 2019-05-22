package com.codebase.core.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class Bundle {

    private static final Logger LOG = LoggerFactory.getLogger(Bundle.class);

    private ClassLoader classLoader;
    private String bundleName;
    private String initClass;
    private BundleInitializer initializer;

    public Bundle(String bundleName, String initClass, ClassLoader loader) {
        this.bundleName = bundleName;
        this.initClass = initClass;
        this.classLoader = loader;
    }

    public void start(BundleContext context) {

        LOG.info("@ start to init bundle {}", bundleName);

        if (initClass == null || initClass.isEmpty()) {
            LOG.info("@ bundle {} has no init class", bundleName);
            return;
        }
        try {
            Class<?> clazz = classLoader.loadClass(initClass);
            initializer = (BundleInitializer) clazz.newInstance();
            initializer.start(context);
            LOG.info("@ finished to init bundle {}", bundleName);
        } catch (Exception e) {
            LOG.warn("@ init bundle {} failed", bundleName, e);
        }
    }

    public void stop(BundleContext context) {
        if (initializer == null) return;
        initializer.stop(context);
        LOG.info("bundle {} stop done", bundleName);
    }

    public String getBundleName() {
        return bundleName;
    }

    public static Bundle create(File bundleDir, String bundleName, SharedClassList sharedClasses, BundleConf conf) throws MalformedURLException {
        BundleClassLoader loader = new BundleClassLoader(bundleDir, sharedClasses);
        List<String> sharedClassNames = conf.getSharedClassNames();
        if (sharedClassNames != null) {
            loadSharedClasses(loader, sharedClasses, sharedClassNames);
        }
        return new Bundle(bundleName, conf.getInitClassName(), loader);
    }

    private static void loadSharedClasses(ClassLoader loader, SharedClassList sharedClasses, List<String> exports) {
        for (String className : exports) {
            LOG.info("load shared classes {}", className);
            try {
                Class<?> clazz = loader.loadClass(className);
                sharedClasses.put(className, clazz);
            } catch (ClassNotFoundException e) {
                LOG.warn("load shared class {} failed", className, e);
            }
        }
    }

}
