package com.example.CarParkApi.Entity;

import javax.persistence.*;

@Entity
@Table(name="permissions")
//@PrimaryKeyJoinColumn(name="permission_id")
public class Permission {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="permission_id",nullable = false,unique = true)
    private long id;

    @Column(name="role",nullable = false,columnDefinition ="VARCHAR(20) DEFAULT 'Employee'" )
    private String role;

    @Column(name="session_id",nullable = true,unique = true)
    private String sessionId;

    @Column(name="access_key",nullable=true,unique = true)
    private String accessKey;

    @OneToOne(mappedBy="permission",fetch = FetchType.LAZY)
    private Employee student;

    public Permission() {
    }

    public Permission(long id, String role, Employee student) {
        this.id = id;
        this.role = role;
        this.student = student;
    }

    public Permission(long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Permission(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee getStudent() {
        return student;
    }

    public void setStudent(Employee student) {
        this.student = student;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
