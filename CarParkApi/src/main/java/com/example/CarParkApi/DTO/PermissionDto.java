package com.example.CarParkApi.DTO;

import java.io.Serializable;
import java.util.Objects;

public class PermissionDto implements Serializable {
    private String role;

    public PermissionDto() {
    }

    public PermissionDto(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionDto entity = (PermissionDto) o;
        return Objects.equals(this.role, entity.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "role = " + role + ")";
    }
}
