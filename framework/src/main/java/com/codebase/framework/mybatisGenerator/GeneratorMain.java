package com.codebase.framework.mybatisGenerator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/22
 */
public class GeneratorMain {

    static final boolean overwrite = true;
    static final String mbgConfig = "/mybatisGenerator/mbgConfiguration.xml";

    public static void main(String[] args) throws Exception {

        File f = new File("hello");
        System.out.println(f.getAbsoluteFile());

        List<String> warnings = new ArrayList<>();
        ConfigurationParser parser = new ConfigurationParser(warnings);

        Configuration config = parser.parseConfiguration(GeneratorMain.class.getResourceAsStream(mbgConfig));

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
    }

}
