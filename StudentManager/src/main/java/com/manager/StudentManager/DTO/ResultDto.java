package com.manager.StudentManager.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ResultDto implements Serializable {
    private Double grade;
    private String semesterName;
    private LocalDate semesterSemesterDate;

    public ResultDto() {
    }

    public ResultDto(Double grade, String semesterName, LocalDate semesterSemesterDate) {
        this.grade = grade;
        this.semesterName = semesterName;
        this.semesterSemesterDate = semesterSemesterDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public LocalDate getSemesterSemesterDate() {
        return semesterSemesterDate;
    }

    public void setSemesterSemesterDate(LocalDate semesterSemesterDate) {
        this.semesterSemesterDate = semesterSemesterDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultDto entity = (ResultDto) o;
        return Objects.equals(this.grade, entity.grade) &&
                Objects.equals(this.semesterName, entity.semesterName) &&
                Objects.equals(this.semesterSemesterDate, entity.semesterSemesterDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, semesterName, semesterSemesterDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "grade = " + grade + ", " +
                "semesterName = " + semesterName + ", " +
                "semesterSemesterDate = " + semesterSemesterDate + ")";
    }
}
