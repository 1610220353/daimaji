package com.haha.generator;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.IOException;

/**
 * Created by wu.bing on 2019/8/14.
 */
public class GeneratorFactory {
    public static void generator() throws IOException {
        AutoGenerator mpg = new AutoGenerator();
        Project project = new Project();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(project.getJavaPath());
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setAuthor("inspur");
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(project.getDbType());
        dsc.setDriverName(project.getDriver());
        dsc.setUsername(project.getUsername());
        dsc.setPassword(project.getPassword());
        dsc.setUrl(project.getUrl());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(project.getTableName()); // 需要生成的表
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(project.getParentPath());
        pc.setModuleName(project.getModule());
        pc.setEntity("model");
        pc.setController("controller");
        pc.setMapper("dao");
        pc.setXml("mapping");
        mpg.setPackageInfo(pc);
        TemplateConfig tc = new TemplateConfig();
        tc.setServiceImpl(null);
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }
}
