package com.example.CarParkApi.DTO;

import java.io.Serializable;
import java.util.Objects;

public class CredentialDto implements Serializable {
    private String account;
    private String password;
    private String role;
    private String sessionId;
    private String accessKey;

    public CredentialDto() {
    }

    public CredentialDto(String account, String password, String role, String sessionId, String accessKey) {
        this.account = account;
        this.password = password;
        this.role = role;
        this.sessionId = sessionId;
        this.accessKey = accessKey;
    }

    public CredentialDto(String account, String role, String sessionId, String accessKey) {
        this.account = account;
        this.role = role;
        this.sessionId = sessionId;
        this.accessKey = accessKey;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialDto entity = (CredentialDto) o;
        return Objects.equals(this.account, entity.account) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.role, entity.role) &&
                Objects.equals(this.sessionId, entity.sessionId) &&
                Objects.equals(this.accessKey, entity.accessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, password, role, sessionId, accessKey);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "account = " + account + ", " +
                "password = " + password + ", " +
                "permissionRole = " + role + ", " +
                "permissionSessionId = " + sessionId + ", " +
                "permissionAccessKey = " + accessKey + ")";
    }
}
