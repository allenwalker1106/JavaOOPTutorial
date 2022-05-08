package com.example.demo.Security;

public enum ApplicationAuthority {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    STUDENT_UPDATE("student:update"),
    STUDENT_DELETE("student:delete"),

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),


    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),
    COURSE_UPDATE("course:update"),
    COURSE_DELETE("course:delete"),
    ;


    private String permission;

    ApplicationAuthority(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
