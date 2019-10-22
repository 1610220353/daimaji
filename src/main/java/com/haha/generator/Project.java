package com.haha.generator;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by wu.bing on 2019/8/14.
 */
@Getter
@Setter
public class Project{
    Properties properties = PropertiesLoaderUtils.loadAllProperties("generator.properties");

    private String driver;
    private String url;
    private String username;
    private String password;
    private String[] tableName;
    private String javaPath;
    private DbType dbType;
    private String parentPath;
    private String module;

    public Project() throws IOException {
        this.driver = properties.getProperty("driver");
        this.url = properties.getProperty("url");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
        this.tableName = properties.getProperty("tableName").split(",");
        this.javaPath = System.getProperty("user.dir")+ File.separator +"out";
        this.dbType = getDbType(properties.getProperty("dbType"));
        this.parentPath = properties.getProperty("parentPath");
        this.module = properties.getProperty("module");
    }

    public DbType getDbType(String str){
        switch (str){
            case "mysql":
                return DbType.MYSQL;
            case "oracle":
                return DbType.ORACLE;
            case "mariadb":
                return DbType.MARIADB;
            case "db2":
                return DbType.DB2;
            case "sql_server":
                return DbType.SQL_SERVER;
            case "h2":
                return DbType.H2;
            case "hsql":
                return DbType.ORACLE;
            case "sqlite":
                return DbType.SQLITE;
            case "postgre_sql":
                return DbType.POSTGRE_SQL;
            default:
                return DbType.OTHER;
        }
    }
}

