package com.manager.StudentManager.Entity;

import com.manager.StudentManager.Entity.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="regular_students")
@PrimaryKeyJoinColumn(name="student_id")
public class RegularStudent extends Student {
    public RegularStudent() {
    }

    public RegularStudent(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, Department department) {
        super(id, name, dateOfBirth, email, admissionYear, admissionGrade, department);
    }

    public RegularStudent(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, Department department, Set<Result> results) {
        super(id, name, dateOfBirth, email, admissionYear, admissionGrade, department, results);
    }
}