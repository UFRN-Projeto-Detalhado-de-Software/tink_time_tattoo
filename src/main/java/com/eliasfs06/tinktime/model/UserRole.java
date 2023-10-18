package com.eliasfs06.tinktime.model;

import java.util.List;

public enum UserRole {

    ADMIN("admin"),
    USER("user"),
    ARTIST("artist");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

}
