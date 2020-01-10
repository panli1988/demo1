package com.example.demo1.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
@DependsOn(value={"mysqlXADataSourceDs1","transactionManager"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDs1",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManager", //配置 事物管理器  transactionManager
        basePackages = {"com.example.demo1.repository.ds1"}
)
public class DataSourceConfigDs1 {

    @Qualifier(value = "mysqlXADataSourceDs1")
    @Autowired
    private MysqlXADataSource mysqlXADataSource;

    @Bean(name = "ds1")
    @Primary
    public DataSource dataSourceDs1(){
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("xads1");
        xaDataSource.setMinPoolSize(10);
        xaDataSource.setPoolSize(50);
        xaDataSource.setMaxPoolSize(50);
        xaDataSource.setBorrowConnectionTimeout(60000);
        xaDataSource.setReapTimeout(60000);
        xaDataSource.setMaxIdleTime(36000);
        xaDataSource.setMaintenanceInterval(60);
        xaDataSource.setMaxLifetime(36000);
        return xaDataSource;
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
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        return builder.dataSource(dataSourceDs1())
                .jta(true)
                .properties(jpaProperties.getProperties())
                .properties(properties)
                .packages("com.example.demo1.repository.ds1")
                //持久化单元名称，当存在多个EntityManagerFactory时，需要制定此名称
                .persistenceUnit("persistenceUnitDs1")
                .build();

    }

//    @Bean("transactionManagerDs1")
//    @Primary
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
//    }
}
