package com.appsterlight.db.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"id", "password"})
@EqualsAndHashCode(exclude = {"id", "password"})
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private transient String password;
    private String role;
    private String description;

}
