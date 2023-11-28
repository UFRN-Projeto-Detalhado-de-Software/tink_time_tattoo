package com.eliasfs06.tinktime.model;

public enum UserRole {

    ADMIN("admin"),
    CLIENT("client"),
    EMPLOYEE("employee");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

}
