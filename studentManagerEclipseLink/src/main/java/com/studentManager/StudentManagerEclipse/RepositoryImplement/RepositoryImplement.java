package com.studentManager.StudentManagerEclipse.RepositoryImplement;

import com.studentManager.StudentManagerEclipse.Entity.Department;
import com.studentManager.StudentManagerEclipse.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public  class RepositoryImplement<K,ID> implements Repository<K,ID> {

    @Autowired
    EntityManager o_entityManager;

    @Override
    public Optional<K> getById(ID id) {
        Query o_query = o_entityManager.createNamedQuery(getEntityName()+".getById");
        o_query.setParameter("id",id);

        List<K> c_results = o_query.getResultList();
        if(c_results.size()==0){
            return Optional.empty();
        }
        return Optional.of((K) c_results.get(0));
    }

    @Override
    public Optional<K> getOne(ID id) {
        return getById(id);
    }

    @Override
    public List<K> findAll() {
        // Query o_query = o_entityMnager.createNamedQuery("Department.findAll")
        Query o_query = o_entityManager.createNamedQuery(getEntityName()+".findAll");
        List<K> c_result = o_query.getResultList();
        return c_result;
    }

    @Override
    public List<K> findAllById(List<ID> ids) {
        Query o_query = o_entityManager.createNamedQuery(getEntityName()+".findAllById");
        o_query.setParameter("ids",ids);
        List<K> c_results = o_query.getResultList();
        return c_results;
    }

    @Override
    public Optional<K> save(K entity) {
        try {
            o_entityManager.getTransaction().begin();
            o_entityManager.persist(entity);
            o_entityManager.getTransaction().commit();
            return Optional.of(entity);
        }catch(RollbackException e){
            return Optional.empty();
        }
    }

    @Override
    public List<K> saveAll(List<K> entities) {

        try {
            o_entityManager.getTransaction().begin();
            for(K entity : entities) {
                o_entityManager.persist(entity);
            }
            o_entityManager.getTransaction().commit();
            return entities;
        }catch(Exception e){
            return List.of();
        }
    }
}
