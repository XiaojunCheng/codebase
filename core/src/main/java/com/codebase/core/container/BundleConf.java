package com.codebase.core.container;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * bundle properties, located at `bundle-name/name.prop`
 */
public class BundleConf {

    private static final String KEY_INIT = "init";
    private static final String KEY_EXPORT_CLASS = "export-class";
    private static final String KEY_IMPORT_CLASS = "import-class";

    private List<String> exportClassNames;
    private List<String> importClassNames;
    private String initClassName;

    public BundleConf(File bundleDir) {
        String name = bundleDir.getName();
        File configFile = new File(bundleDir.getAbsolutePath() + File.separator + name + ".prop");
        if (!configFile.exists()) {
            throw new IllegalArgumentException("bundle prop file not exist");
        }
        load(configFile);
    }

    public List<String> getExportClassNames() {
        return exportClassNames;
    }

    public List<String> getImportClassNames() {
        return importClassNames;
    }

    public String getInitClassName() {
        return initClassName;
    }

    private void load(File configFile) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configFile));
        } catch (IOException e) {
            throw new IllegalArgumentException("load bundle config file " + configFile.getAbsolutePath() + "failed");
        }
        initClassName = properties.getProperty(KEY_INIT);
        exportClassNames = parseList(KEY_EXPORT_CLASS, properties);
        importClassNames = parseList(KEY_IMPORT_CLASS, properties);
    }

    private List<String> parseList(String key, Properties properties) {
        String value = properties.getProperty(key);
        if (value == null)
            return Collections.emptyList();

        List<String> list = new ArrayList<>();
        String[] parts = value.split(";");
        for (String part : parts) {
            list.add(part);
        }
        return list;
    }
}
