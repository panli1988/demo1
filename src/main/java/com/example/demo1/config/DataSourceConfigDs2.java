package com.example.demo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * @author Administrator
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDs2",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerDs2", //配置 事物管理器  transactionManager
        basePackages = {"com.example.demo1.repository.ds2"}
)
public class DataSourceConfigDs2 {

    @Bean(name = "ds2")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource dataSourceDs2(){
        return DataSourceBuilder.create().build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    @Bean("entityManagerDs2")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBean(builder).getObject().createEntityManager();
    }
    @Bean(value = "entityManagerFactoryDs2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSourceDs2())
                .properties(jpaProperties.getProperties())
                .packages("com.example.demo1.repository.ds2")
                //持久化单元名称，当存在多个EntityManagerFactory时，需要制定此名称
                .persistenceUnit("persistenceUnitDs2")
                .build();

    }

    @Bean("transactionManagerDs2")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
    }
}
