package com.example.hoduBoard.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }
    public String value() {
        return role;
    }
}