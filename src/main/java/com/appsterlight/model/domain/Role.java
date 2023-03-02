package com.appsterlight.model.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_USER, ROLE_MANAGER, ROLE_GUEST;


}
