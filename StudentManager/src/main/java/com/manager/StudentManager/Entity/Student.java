package com.manager.StudentManager.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="students")
@Inheritance(strategy = InheritanceType.JOINED)
public class Student {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "student_name", nullable = false, length = 100)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "admission_year", nullable = false)
    private Integer admissionYear;

    @Column(name = "admission_grade", nullable = false)
    private Double admissionGrade;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @OneToMany(mappedBy="student")
    private Set<Result> results;

    public Student() {
    }

    public Student(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, Department department) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.admissionYear = admissionYear;
        this.admissionGrade = admissionGrade;
        this.department = department;
        this.results = new HashSet<Result>();
    }

    public Student(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, Department department, Set<Result> results) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.admissionYear = admissionYear;
        this.admissionGrade = admissionGrade;
        this.department = department;
        this.results = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }

    public Double getAdmissionGrade() {
        return admissionGrade;
    }

    public void setAdmissionGrade(Double admissionGrade) {
        this.admissionGrade = admissionGrade;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", admissionYear=" + admissionYear +
                ", admissionGrade=" + admissionGrade +
                ", department=" + department +
                ", results=" + results +
                '}';
    }
}