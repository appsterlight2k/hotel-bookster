package com.appsterlight.controller.action.utils;

import com.appsterlight.model.domain.User;

public class ValidationUtils {
    public static boolean isNewUserValid(User user) {
        boolean firstname = user.getFirstName() != null;
        boolean lastname = user.getLastName() != null;
        boolean email = user.getEmail() != null;
        boolean password = user.getPassword() != null;
        boolean role = user.getRole() != null;

        return firstname && lastname && email && password && role;
    }
}
