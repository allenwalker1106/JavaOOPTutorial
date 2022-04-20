package com.studentManager.StudentManagerEclipse.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "service_students")
@PrimaryKeyJoinColumn(name="student_id")
public class ServiceStudent extends Student {


    @ManyToOne
    @JoinColumn(name="training_site_id")
    private TrainingSite trainingSite;



    public ServiceStudent() {
    }

    public ServiceStudent(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, Department department, TrainingSite trainingSite) {
        super(id, name, dateOfBirth, email, admissionYear, admissionGrade, department);
        this.trainingSite = trainingSite;
    }

    public ServiceStudent(Long id, String name, LocalDate dateOfBirth, String email, Integer admissionYear, Double admissionGrade, Department department, Set<Result> results, TrainingSite trainingSite) {
        super(id, name, dateOfBirth, email, admissionYear, admissionGrade, department, results);
        this.trainingSite = trainingSite;
    }

    public TrainingSite getTrainingSite() {
        return trainingSite;
    }

    public void setTrainingSite(TrainingSite trainingSite) {
        this.trainingSite = trainingSite;
    }

    @Override
    public String toString() {
        return "ServiceStudent{" +
                "trainingSite=" + trainingSite +
                '}';
    }
}