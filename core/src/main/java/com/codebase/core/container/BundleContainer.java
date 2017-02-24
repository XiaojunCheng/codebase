package com.codebase.core.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

/*
 * bundle container directory:
 * -- lib
 * -- bundle
 *    |-- app
 *        |-- bundle.prop
 *        |-- lib
 */
public class BundleContainer {

    private static final Logger LOG = LoggerFactory.getLogger(BundleContainer.class);

    private static final String BUNDLE_PATH = "bundle";

    private final SharedClassList sharedClasses = new SharedClassList();
    private final Map<String, Bundle> bundles = new HashMap<>();

    public BundleContainer() {
    }

    public void start() {
        loadBundles();
        startBundles();
    }

    public void stop() {
        stopBundles();
        sharedClasses.clear();
        bundles.clear();
    }

    public Map<String, Bundle> getBundles() {
        return bundles;
    }

    private void loadBundles() {

        LOG.info("load bundles...");

        File root = new File(BUNDLE_PATH);
        if (!root.exists()) {
            LOG.error("bundle path not exists");
            throw new IllegalArgumentException("bundle path not exists");
        }

        File[] bundleDirs = root.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        for (File bundleDir : bundleDirs) {
            Bundle bundle = loadBundle(bundleDir);
            if (bundle != null) {
                LOG.info("load bundle {} done", bundle.getBundleName());
                bundles.put(bundle.getBundleName(), bundle);
            }
        }
    }

    private Bundle loadBundle(File bundleDir) {
        try {
            BundleConf bundleConf = new BundleConf(bundleDir);
            return Bundle.create(bundleDir, bundleDir.getName(), sharedClasses, bundleConf);
        } catch (Exception e) {
            LOG.warn("load bundle {} failed", bundleDir.getName(), e);
            return null;
        }
    }

    private void startBundles() {
        LOG.info("start all bundles");
        for (Bundle bundle : bundles.values()) {
            BundleContext context = new BundleContext();
            context.setName(bundle.getBundleName());
            bundle.start(context);
        }
    }

    private void stopBundles() {
        LOG.info("stop all bundles");
        for (Bundle bundle : bundles.values()) {
            BundleContext context = new BundleContext();
            context.setName(bundle.getBundleName());
            bundle.stop(context);
        }
    }

}
