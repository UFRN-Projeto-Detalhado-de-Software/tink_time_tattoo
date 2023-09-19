package com.eliasfs06.tinktime.model;

public enum UserRole {

    ADMIN("admin"),
    USER("user"),
    ARTIST("artist");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

}
