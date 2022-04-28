package com.manager.CarPark.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeDto implements Serializable {
    private long id;
    private String account;
    private String department;
    private String address;
    private LocalDate date;
    private String email;
    private String name;
    private String phoneNumber;
    private String gender;
    private PermissionDto permission;

    public EmployeeDto() {
    }

    public EmployeeDto(long id, String account, String department, String address, LocalDate date, String email, String name, String phoneNumber, String gender, PermissionDto permission) {
        this.id = id;
        this.account = account;
        this.department = department;
        this.address = address;
        this.date = date;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PermissionDto getPermission() {
        return permission;
    }

    public void setPermission(PermissionDto permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto entity = (EmployeeDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.account, entity.account) &&
                Objects.equals(this.department, entity.department) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.permission, entity.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, department, address, date, email, name, phoneNumber, gender, permission);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "account = " + account + ", " +
                "department = " + department + ", " +
                "address = " + address + ", " +
                "date = " + date + ", " +
                "email = " + email + ", " +
                "name = " + name + ", " +
                "phoneNumber = " + phoneNumber + ", " +
                "gender = " + gender + ", " +
                "permission = " + permission + ")";
    }
}
