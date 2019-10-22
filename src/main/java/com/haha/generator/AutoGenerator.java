package com.haha.generator;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wu.bing on 2019/8/14.
 */
public class AutoGenerator {
    private static final Logger logger = LoggerFactory.getLogger(com.baomidou.mybatisplus.generator.AutoGenerator.class);
    protected ConfigBuilder config;
    protected InjectionConfig injectionConfig;
    private DataSourceConfig dataSource;
    private StrategyConfig strategy;
    private PackageConfig packageInfo;
    private TemplateConfig template;
    private GlobalConfig globalConfig;
    private AbstractTemplateEngine templateEngine;

    public AutoGenerator() {
    }

    public void execute() {
        logger.debug("==========================准备生成文件...==========================");
        if(null == this.config) {
            this.config = new ConfigBuilder(this.packageInfo, this.dataSource, this.strategy, this.template, this.globalConfig);
            if(null != this.injectionConfig) {
                this.injectionConfig.setConfig(this.config);
            }
        }

        if(null == this.templateEngine) {
            this.templateEngine = new VelocityTemplateEngine();
        }

        this.templateEngine.init(this.pretreatmentConfigBuilder(this.config)).mkdirs().batchOutput();
        logger.debug("==========================文件生成完成！！！==========================");
    }

    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        return config.getTableInfoList();
    }

    protected ConfigBuilder pretreatmentConfigBuilder(ConfigBuilder config) {
        if(null != this.injectionConfig) {
            this.injectionConfig.initMap();
            config.setInjectionConfig(this.injectionConfig);
        }

        List<TableInfo> tableList = this.getAllTableInfoList(config);
        Iterator i$ = tableList.iterator();

        while(true) {
            TableInfo tableInfo;
            do {
                if(!i$.hasNext()) {
                    return config.setTableInfoList(tableList);
                }

                tableInfo = (TableInfo)i$.next();
                if(config.getGlobalConfig().isActiveRecord()) {
                    tableInfo.setImportPackages(Model.class.getCanonicalName());
                }

                if(tableInfo.isConvert()) {
                    tableInfo.setImportPackages(TableName.class.getCanonicalName());
                }

                if(tableInfo.isLogicDelete(config.getStrategyConfig().getLogicDeleteFieldName())) {
                    tableInfo.setImportPackages(TableLogic.class.getCanonicalName());
                }

                if(StringUtils.isNotEmpty(config.getStrategyConfig().getVersionFieldName())) {
                    tableInfo.setImportPackages(Version.class.getCanonicalName());
                }

                if(StringUtils.isNotEmpty(config.getSuperEntityClass())) {
                    tableInfo.setImportPackages(config.getSuperEntityClass());
                } else {
                    tableInfo.setImportPackages(Serializable.class.getCanonicalName());
                }
            } while(!config.getStrategyConfig().isEntityBooleanColumnRemoveIsPrefix());

            Iterator iterator = tableInfo.getFields().iterator();

            while(iterator.hasNext()) {
                TableField field = (TableField)i$.next();
                if(field.getPropertyType().equalsIgnoreCase("boolean") && field.getPropertyName().startsWith("is")) {
                    field.setPropertyName(config.getStrategyConfig(), StringUtils.removePrefixAfterPrefixToLower(field.getPropertyName(), 2));
                }
            }
        }
    }

    public DataSourceConfig getDataSource() {
        return this.dataSource;
    }

    public AutoGenerator setDataSource(DataSourceConfig dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public StrategyConfig getStrategy() {
        return this.strategy;
    }

    public AutoGenerator setStrategy(StrategyConfig strategy) {
        this.strategy = strategy;
        return this;
    }

    public PackageConfig getPackageInfo() {
        return this.packageInfo;
    }

    public AutoGenerator setPackageInfo(PackageConfig packageInfo) {
        this.packageInfo = packageInfo;
        return this;
    }

    public TemplateConfig getTemplate() {
        return this.template;
    }

    public AutoGenerator setTemplate(TemplateConfig template) {
        this.template = template;
        return this;
    }

    public ConfigBuilder getConfig() {
        return this.config;
    }

    public AutoGenerator setConfig(ConfigBuilder config) {
        this.config = config;
        return this;
    }

    public GlobalConfig getGlobalConfig() {
        return this.globalConfig;
    }

    public AutoGenerator setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    public InjectionConfig getCfg() {
        return this.injectionConfig;
    }

    public AutoGenerator setCfg(InjectionConfig injectionConfig) {
        this.injectionConfig = injectionConfig;
        return this;
    }

    public AbstractTemplateEngine getTemplateEngine() {
        return this.templateEngine;
    }

    public AutoGenerator setTemplateEngine(AbstractTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        return this;
    }
}
