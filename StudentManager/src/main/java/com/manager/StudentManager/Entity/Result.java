package com.manager.StudentManager.Entity;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="result_sequence")
    @SequenceGenerator(name="result_sequence", sequenceName="result_sequence",allocationSize=100)
    @Column(name="result_id",nullable=false,unique=true)
    private Long id;

    @Column(name="average_grade",nullable = false)
    private Double grade;

    @ManyToOne
    @JoinColumn(name="semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    public Result() {
    }

    public Result(Long id, Double grade, Semester semester, Student student) {
        this.id = id;
        this.grade = grade;
        this.semester = semester;
        this.student = student;
    }

    public Result(Double grade, Semester semester, Student student) {
        this.grade = grade;
        this.semester = semester;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", grade=" + grade +
                ", semester=" + semester +
                ", student=" + student +
                '}';
    }
}