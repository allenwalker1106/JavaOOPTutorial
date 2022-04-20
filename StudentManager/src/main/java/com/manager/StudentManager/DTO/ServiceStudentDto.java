package com.manager.StudentManager.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class ServiceStudentDto extends StudentDto implements Serializable {
    private String trainingSiteName;

    public ServiceStudentDto() {
    }

    public ServiceStudentDto(String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, String departmentName, Set<ResultDto> results, String trainingSiteName) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.admissionYear = admissionYear;
        this.admissionGrade = admissionGrade;
        this.departmentName = departmentName;
        this.results = results;
        this.trainingSiteName = trainingSiteName;
    }

    public String getTrainingSiteName() {
        return trainingSiteName;
    }

    public void setTrainingSiteName(String trainingSiteName) {
        this.trainingSiteName = trainingSiteName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceStudentDto entity = (ServiceStudentDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.dateOfBirth, entity.dateOfBirth) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.admissionYear, entity.admissionYear) &&
                Objects.equals(this.admissionGrade, entity.admissionGrade) &&
                Objects.equals(this.departmentName, entity.departmentName) &&
                Objects.equals(this.results, entity.results) &&
                Objects.equals(this.trainingSiteName, entity.trainingSiteName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, email, admissionYear, admissionGrade, departmentName, results, trainingSiteName);
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
                "results = " + results + ", " +
                "trainingSiteName = " + trainingSiteName + ")";
    }
}
