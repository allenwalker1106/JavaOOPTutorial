package com.studentManager.StudentManagerEclipse.Configurator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Configuration
public class JpaConfigurator {

    @Bean
    public EntityManager entityManager(){
        EntityManagerFactory o_entityManagerFactory = Persistence.createEntityManagerFactory("student-manager-persistence");
        EntityManager o_entityManager = o_entityManagerFactory.createEntityManager();
        return o_entityManager;
    }
}
