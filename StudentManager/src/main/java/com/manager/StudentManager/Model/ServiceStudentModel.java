package com.manager.StudentManager.Model;

import java.time.LocalDate;

public class ServiceStudentModel extends StudentModel{
    private String trainingSite;

    public ServiceStudentModel() {
    }

    public ServiceStudentModel(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, String departmentId, String trainingSite) {
        super(id, name, dateOfBirth, email, admissionYear, admissionGrade, departmentId);
        this.trainingSite = trainingSite;
    }

    public ServiceStudentModel(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, String departmentId, String trainingSite, String trainingSite1) {
        super(id, name, dateOfBirth, email, admissionYear, admissionGrade, departmentId, trainingSite);
        this.trainingSite = trainingSite1;
    }

    public String getTrainingSite() {
        return trainingSite;
    }

    public void setTrainingSite(String trainingSite) {
        this.trainingSite = trainingSite;
    }
}
