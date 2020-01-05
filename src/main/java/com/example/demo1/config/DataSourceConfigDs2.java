package com.example.demo1.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
@DependsOn(value={"mysqlXADataSourceDs2","transactionManager"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDs2",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManager", //配置 事物管理器  transactionManager
        basePackages = {"com.example.demo1.repository.ds2"}
)
public class DataSourceConfigDs2 {

    @Qualifier(value = "mysqlXADataSourceDs2")
    @Autowired
    private MysqlXADataSource mysqlXADataSource;

    @Bean(name = "ds2")
    public DataSource dataSourceDs2(){
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("xads2");
        xaDataSource.setMinPoolSize(10);
        xaDataSource.setPoolSize(50);
        xaDataSource.setMaxPoolSize(50);
        xaDataSource.setBorrowConnectionTimeout(60000);
        xaDataSource.setReapTimeout(60000);
        xaDataSource.setMaxIdleTime(3600);
        xaDataSource.setMaintenanceInterval(60);
        return xaDataSource;
    }

    @Autowired
    private JpaProperties jpaProperties;

    @Bean("entityManagerDs2")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBean(builder).getObject().createEntityManager();
    }
    @Bean(value = "entityManagerFactoryDs2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        return builder.dataSource(dataSourceDs2())
                .jta(true)
                .properties(jpaProperties.getProperties())
                .properties(properties)
                .packages("com.example.demo1.repository.ds2")
                //持久化单元名称，当存在多个EntityManagerFactory时，需要制定此名称
                .persistenceUnit("persistenceUnitDs2")
                .build();

    }

//    @Bean("transactionManagerDs2")
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
//    }
}
