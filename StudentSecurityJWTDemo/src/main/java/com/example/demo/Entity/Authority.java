package com.example.demo.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="authorities")
public class Authority {
    @Id
    @Column(name="permission",nullable=false,unique = true)
    private String permission;

    @ManyToMany(mappedBy = "authorities",fetch=FetchType.LAZY)
    Set<User> users = Set.of();

    public Authority() {
    }

    public Authority(String permission) {
        this.permission = permission;
    }

    public Authority(String permission,Set<User> users) {
        this.permission = permission;
        this.users = users;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "permission='" + permission + '\'' +
                ", users=" + users +
                '}';
    }
}
