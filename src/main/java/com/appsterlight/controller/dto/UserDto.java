package com.appsterlight.controller.dto;

import com.appsterlight.model.domain.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Builder
@Data
@EqualsAndHashCode(of = {"firstName", "lastName","email"})
public class UserDto implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String role;

}
