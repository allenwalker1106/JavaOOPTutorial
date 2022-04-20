package com.manager.StudentManager.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="departments")
public class Department {
    @Id
    @Column(name="department_id",unique=true,nullable=false)
    private String id;

    @Column(name="department_name",unique=true,nullable = false)
    private String name;

    @OneToMany(mappedBy="department")
    private Set<Student> students;

    public Department() {
    }

    public Department(String id) {
        this.id = id;
    }

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(String id, String name, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
