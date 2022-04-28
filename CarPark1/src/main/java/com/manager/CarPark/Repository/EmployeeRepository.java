package com.manager.CarPark.Repository;

import com.manager.CarPark.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByAccount(String account);
    List<Employee> findByName(String name);
    List<Employee> findByDepartmentContains(@Param("department") String department);
    List<Employee> findByNameContains(@Param("name")String name);
    List<Employee> findByEmail(String email);
    List<Employee> findByPhoneNumber(String phoneNumber);
}
