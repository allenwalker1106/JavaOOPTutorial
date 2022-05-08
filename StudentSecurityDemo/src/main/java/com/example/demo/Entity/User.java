package com.example.demo.Entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.thymeleaf.expression.Sets;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(generator="user_sequence")
    @GenericGenerator(
            name="user_sequence",
            strategy= "org.hibernate.id.enhanced.SequenceStyleGenerator"
    )
    @Column(name="uid",nullable=false,unique = true)
    private Long id;

    @Column(name="username",nullable=false,unique=true)
    private String account;

    @Column(name="password",nullable=false)
    private String password;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="email",nullable=false,unique=true)
    private String email;

    @Column(name="phone_number",nullable=false,unique=true)
    private String phone;

    @Column(name="address")
    private String address;

    @Column(name="date_of_birth")
    private LocalDate date;

    @Column(name="account_expired")
    boolean accountExpired;

    @Column(name="is_lock")
    boolean lock;

    @Column(name="is_credential_expired")
    boolean credentialExpired;

    @Column(name="is_enable")
    boolean enable=true;


    @Transient
    private Integer age;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_authorities",
            joinColumns={@JoinColumn(name="uid")},
            inverseJoinColumns={@JoinColumn(name="auth_id")}
    )
    Set<Authority> authorities = Set.of();

    public User() {
    }

    public User(String account, String password, String name, String email, String phone, String address, LocalDate date) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.date = date;
    }

    public User(String account, String password, String name, String email, String phone, String address, LocalDate date, boolean accountExpired, boolean lock, boolean credentialExpired, boolean enable, Integer age) {
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
        this.age = age;
    }

    public User(Long id, String account, String password, String name, String email, String phone, String address, LocalDate date, boolean accountExpired, boolean lock, boolean credentialExpired, boolean enable, Integer age, Set<Authority> authorities) {
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
        this.age = age;
        this.authorities = authorities;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
