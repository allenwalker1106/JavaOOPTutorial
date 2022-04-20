package com.studentManager.StudentManagerEclipse.Repository;

import com.studentManager.StudentManagerEclipse.Repository.CrudRepository;

import java.lang.reflect.ParameterizedType;

public interface Repository<K,ID> extends CrudRepository<K,ID> {
    default String getEntityName() {
        return ((Class<K>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}
