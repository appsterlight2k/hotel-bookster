package com.appsterlight.model.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_USER("role_user"), ROLE_MANAGER("role_manager"), ROLE_UNKNOWN("UNKNOWN");

    private final String role;

    public static Role get(String role) {
        return Role.get(role.toUpperCase());
    }

}
