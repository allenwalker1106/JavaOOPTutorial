package com.manager.CarPark.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="employee_id",nullable=false,unique = true)
    private long id;

    @Column(name="account",nullable=false,unique = true)
    private String account;

    @Column(name="department",nullable=false)
    private String department;

    @Column(name="address",nullable=false)
    private String address;

    @Column(name="date_of_birth",nullable=false)
    private LocalDate date;

    @Column(name="email",nullable=false,unique = true)
    private String email;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="phone_number",nullable=false,unique=true)
    private String phoneNumber;

    @Column(name="password",nullable=false)
    private String password;

    @Column(name="gender")
    private String gender;

    @OneToOne
    @JoinColumn(name="permission_id")
    private Permission permission;

    public Employee() {
    }

    public Employee(String account, String department, String address, LocalDate date, String email, String name, String phoneNumber, String password, String gender, Permission permission) {
        this.account = account;
        this.department = department;
        this.address = address;
        this.date = date;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", department='" + department + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", permission=" + permission +
                '}';
    }
}
