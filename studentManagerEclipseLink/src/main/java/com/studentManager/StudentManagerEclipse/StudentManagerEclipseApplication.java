package com.studentManager.StudentManagerEclipse;

import com.studentManager.StudentManagerEclipse.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@SpringBootApplication
public class StudentManagerEclipseApplication {



	public static void main(String[] args) {
		SpringApplication.run(StudentManagerEclipseApplication.class, args);
	}

}
