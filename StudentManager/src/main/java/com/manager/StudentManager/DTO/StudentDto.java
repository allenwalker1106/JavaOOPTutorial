package com.manager.StudentManager.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class StudentDto implements Serializable {

    protected String name;
    protected LocalDate dateOfBirth;
    protected String email;
    protected Integer admissionYear;
    protected Double admissionGrade;
    protected String departmentName;
    protected Set<ResultDto> results;

    public StudentDto() {
    }

    public StudentDto(String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, String departmentName, Set<ResultDto> results) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.admissionYear = admissionYear;
        this.admissionGrade = admissionGrade;
        this.departmentName = departmentName;
        this.results = results;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<ResultDto> getResults() {
        return results;
    }

    public void setResults(Set<ResultDto> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto entity = (StudentDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.dateOfBirth, entity.dateOfBirth) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.admissionYear, entity.admissionYear) &&
                Objects.equals(this.admissionGrade, entity.admissionGrade) &&
                Objects.equals(this.departmentName, entity.departmentName) &&
                Objects.equals(this.results, entity.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, email, admissionYear, admissionGrade, departmentName, results);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "dateOfBirth = " + dateOfBirth + ", " +
                "email = " + email + ", " +
                "admissionYear = " + admissionYear + ", " +
                "admissionGrade = " + admissionGrade + ", " +
                "departmentName = " + departmentName + ", " +
                "results = " + results + ")";
    }
}
