package com.manager.CarPark.Configuration;


import com.manager.CarPark.Entity.Employee;
import com.manager.CarPark.Entity.Permission;
import com.manager.CarPark.Service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class EmployeeConfiguration {



    @Bean("employeeInitializer")
    public CommandLineRunner commandLineRunner(EmployeeService o_employeeService){
        return args->{
            try {
                Permission o_permission = new Permission("Admin");
                Employee o_employee = new Employee("admin", "America", "26 Whale Street, District 4", LocalDate.of(1999, Month.JUNE, 6), "admin@gmail.com", "Allen Walker", "0334910738", "admin", "Male", o_permission);
                o_employeeService.save(o_employee);
                Permission o_permission1 = new Permission("Employee");
                Employee o_employee1 = new Employee("emp", "Australia", "26 Whale Street, District 4", LocalDate.of(1999, Month.JUNE, 6), "emp@gmail.com", "Nicolas Lou", "0334810738", "emp", "Male", o_permission1);
                o_employeeService.save(o_employee1);
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }
}
