package com.example.demo1.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name = "mysqlXADataSourceDs1")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public MysqlXADataSource mysqlXADataSource1(){
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        return mysqlXaDataSource;
    }

    @Bean(name = "mysqlXADataSourceDs2")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public MysqlXADataSource mysqlXADataSource2(){
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        return mysqlXaDataSource;
    }
}
