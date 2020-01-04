package com.example.demo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author Administrator
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDs1",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerDs1", //配置 事物管理器  transactionManager
        basePackages = {"com.example.demo1.repository.ds1"}
)
public class DataSourceConfigDs1 {

    @Bean(name = "ds1")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource dataSourceDs1(){
        return DataSourceBuilder.create().build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    @Bean("entityManagerDs1")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBean(builder).getObject().createEntityManager();
    }
    @Bean("entityManagerFactoryDs1")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSourceDs1())
                .properties(jpaProperties.getProperties())
                .packages("com.example.demo1.repository.ds1")
                //持久化单元名称，当存在多个EntityManagerFactory时，需要制定此名称
                .persistenceUnit("persistenceUnitDs1")
                .build();

    }

    @Bean("transactionManagerDs1")
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
    }
}
