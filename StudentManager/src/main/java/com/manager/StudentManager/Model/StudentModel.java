package com.manager.StudentManager.Model;

import java.time.LocalDate;

public class StudentModel {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private Integer admissionYear;
    private Double admissionGrade;
    private String departmentId;
    private String trainingSite;

    public StudentModel() {
    }

    public StudentModel(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, String departmentId) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.admissionYear = admissionYear;
        this.admissionGrade = admissionGrade;
        this.departmentId = departmentId;
    }

    public StudentModel(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, String departmentId, String trainingSite) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.admissionYear = admissionYear;
        this.admissionGrade = admissionGrade;
        this.departmentId = departmentId;
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

}
