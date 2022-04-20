package com.studentManager.StudentManagerEclipse.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "semesters")
@NamedQueries({
        @NamedQuery(name="Semester.getById",query="SELECT s FROM Semester s WHERE s.id=:id"),
        @NamedQuery(name="Semester.findAll",query="SELECT s FROM Semester s"),
        @NamedQuery(name="Semester.findAllById",query="SELECT s FROM Semester s WHERE s.id IN :ids")
})
public class Semester {
    @Id
    @Column(name="semester_id",nullable=false,unique=true)
    private Long id;

    @Column(name="semester_name",unique=true,nullable=false)
    private String name;

    @Column(name="semester_date")
    private LocalDate semesterDate;

    @OneToMany(mappedBy="semester")
    private Set<Result> results;

    public Semester() {
    }

    public Semester(Long id, String name, LocalDate semesterDate) {
        this.id = id;
        this.name = name;
        this.semesterDate = semesterDate;
    }

    public Semester(Long id, String name, LocalDate semesterDate, Set<Result> results) {
        this.id = id;
        this.name = name;
        this.semesterDate = semesterDate;
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

    public LocalDate getSemesterDate() {
        return semesterDate;
    }

    public void setSemesterDate(LocalDate semesterDate) {
        this.semesterDate = semesterDate;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", semesterDate=" + semesterDate +
                ", results=" + results +
                '}';
    }
}