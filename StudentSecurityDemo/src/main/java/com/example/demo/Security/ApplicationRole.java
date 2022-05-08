package com.example.demo.Security;

import com.example.demo.Entity.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.Security.ApplicationAuthority.*;


public enum ApplicationRole {
    ADMIN(Set.of(USER_READ,USER_WRITE,USER_UPDATE,USER_DELETE)),
    STAFF(Set.of(STUDENT_READ,STUDENT_WRITE,STUDENT_UPDATE,STUDENT_DELETE,COURSE_WRITE,COURSE_UPDATE,COURSE_DELETE)),
    STUDENT(Set.of(COURSE_READ));

    private Set<ApplicationAuthority> authorities;

    ApplicationRole(Set<ApplicationAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<GrantedAuthority> getGrantedAuthority(){
        Set<GrantedAuthority> c_authorities = authorities.stream()
                .map(author->new SimpleGrantedAuthority(author.getPermission()))
                .collect(Collectors.toSet());
        c_authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return c_authorities;
    }

    public Set<Authority> getAuthorities(){
        Set<Authority> c_authorities = authorities.stream()
                .map(author->new Authority(author.getPermission()))
                .collect(Collectors.toSet());
        return c_authorities;
    }
}
