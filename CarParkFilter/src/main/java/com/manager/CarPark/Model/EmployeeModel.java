package com.manager.CarPark.Model;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeModel implements Serializable {
    private Long id;
    private String account;
    private String department;
    private String address;
    private String date;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
    private String gender;

    public EmployeeModel() {
    }

    public EmployeeModel(String account, String department, String address, String date, String email, String name, String phoneNumber, String password, String gender) {
        this.account = account;
        this.department = department;
        this.address = address;
        this.date = date;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeModel entity = (EmployeeModel) o;
        return Objects.equals(this.account, entity.account) &&
                Objects.equals(this.department, entity.department) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.gender, entity.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, department, address, date, email, name, phoneNumber, password, gender);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "account = " + account + ", " +
                "department = " + department + ", " +
                "address = " + address + ", " +
                "date = " + date + ", " +
                "email = " + email + ", " +
                "name = " + name + ", " +
                "phoneNumber = " + phoneNumber + ", " +
                "password = " + password + ", " +
                "gender = " + gender + ")";
    }
}
