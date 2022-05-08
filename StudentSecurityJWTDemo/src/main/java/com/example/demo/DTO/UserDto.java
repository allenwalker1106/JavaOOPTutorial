package com.example.demo.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class UserDto implements Serializable {
    private Long id;
    private String account;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate date;
    private boolean accountExpired;
    private boolean lock;
    private boolean credentialExpired;
    private boolean enable = true;
    private String role;

    public UserDto() {
    }

    public UserDto(String account, String password, String name, String email, String phone, String address, LocalDate date, String role) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.role = role;
    }

    public UserDto(Long id, String account, String password, String name, String email, String phone, String address, LocalDate date, boolean accountExpired, boolean lock, boolean credentialExpired, boolean enable, String role) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.accountExpired = accountExpired;
        this.lock = lock;
        this.credentialExpired = credentialExpired;
        this.enable = enable;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean isCredentialExpired() {
        return credentialExpired;
    }

    public void setCredentialExpired(boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", accountExpired=" + accountExpired +
                ", lock=" + lock +
                ", credentialExpired=" + credentialExpired +
                ", enable=" + enable +
                ", role='" + role + '\'' +
                '}';
    }
}
