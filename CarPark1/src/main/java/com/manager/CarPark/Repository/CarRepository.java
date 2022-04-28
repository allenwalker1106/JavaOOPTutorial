package com.manager.CarPark.Repository;

import com.manager.CarPark.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {
    List<Car> findByIdContains(@Param("id") String id);
    List<Car> findByCompanyContains(@Param("company") String company);
}
