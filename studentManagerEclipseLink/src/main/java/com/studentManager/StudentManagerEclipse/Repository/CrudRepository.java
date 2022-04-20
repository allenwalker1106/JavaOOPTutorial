package com.studentManager.StudentManagerEclipse.Repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<K,I> {
    Optional<K> getById(I id);
    Optional<K> getOne(I id);
    List<K> findAll();
    List<K> findAllById(List<I> ids);
    Optional<K>  save(K entity);
    List<K> saveAll(List<K> entities);
}
