package com.studentManager.StudentManagerEclipse.Configurator;


import com.studentManager.StudentManagerEclipse.Entity.Department;
import com.studentManager.StudentManagerEclipse.RepositoryImplement.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartmentConfiguration {

    @Bean("departmentInitializer")
    public CommandLineRunner commandLineRunner(DepartmentRepository departmentRepository){
        return args->{
            departmentRepository.saveAll(
                    List.of(
                            new Department("CNTT","Cong Nghe Thong Tin"),
                            new Department("DTVT","Dien Tu Vien Thong"),
                            new Department("VLKT","Vat Ly Ky Thuat"),
                            new Department("CNN","Cong Nghe NANO"),
                            new Department("CHKT","Co Hoc Ky Thuat"),
                            new Department("TDH","Tu Dong Hoa"),
                            new Department("CNNN","Cong Nghe Nong Nghiep"),
                            new Department("CNHKVT","Cong Nghe Hang Khong Vu Tru")
                    )
            );
        };
    }
}
