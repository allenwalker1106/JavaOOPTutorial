package com.manager.StudentManager.Configurator;

import org.eclipse.persistence.config.BatchWriting;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.manager.StudentManager.Repository")
public class JpaConfiguration extends JpaBaseConfiguration {

    protected JpaConfiguration(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
        super(dataSource, properties, jtaTransactionManager);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        final Map<String,Object> c_properties =  new HashMap<>();
        c_properties.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
        return c_properties;
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactory(EntityManagerFactoryBuilder o_builder, DataSource o_dataSource){
        return o_builder
                .dataSource(o_dataSource)
                .packages("com.manager.StudentManager.Entity")
                .persistenceUnit("default")
                .properties(this.initJpaProperties()).build();
    }


    private static Map<String,Object> initJpaProperties(){
        final Map<String, Object> c_properties = new HashMap<>();
        c_properties.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);

        c_properties.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINEST_LABEL);

        c_properties.put(PersistenceUnitProperties.WEAVING, "false");

        c_properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);

        c_properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_DATABASE_GENERATION);
        return c_properties;
    }

    @Bean
    @Primary
    public static JpaProperties properties(){
        final JpaProperties o_jpaProperties = new JpaProperties();
        o_jpaProperties.setDatabasePlatform("org.eclipse.persistence.platform.database.MySQLPlatform");
        return o_jpaProperties;
    }

    @Bean
    public static PlatformTransactionManager transactionManager(EntityManagerFactory o_entityManagerFactory){
        final JpaTransactionManager o_transactionManager = new JpaTransactionManager();
        o_transactionManager.setEntityManagerFactory(o_entityManagerFactory);
        return o_transactionManager;
    }

    @Bean
    public static DataSource dataSource(){
        final DriverManagerDataSource o_dataSource = new DriverManagerDataSource();
        o_dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        o_dataSource.setUrl("jdbc:mysql://localhost:3306/studentmanager");
        o_dataSource.setUsername("test");
        o_dataSource.setPassword("thinhHANOI123");
        return o_dataSource;
    }




}
