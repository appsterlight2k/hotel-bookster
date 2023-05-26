package com.appsterlight.controller.action.utils;

import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Role;
import com.appsterlight.model.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SessionUtils {
    public static void SignInUserIntoSession(User user, HttpSession session) throws ServiceException {
        try {
            session.setAttribute("loggedUser", DtoUtils.mapUserToDto(Optional.of(user)));
            session.setAttribute("error", "");
            log.info(String.format("* User with email %s logged in successfully!", user.getEmail()));
        } catch (ServiceException e) {
            log.error("Exception while SignIn user! ", e);
            throw new ServiceException(e);
        }
    }

    public static void ShutDownSession(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loggedUser");
        if (user != null) {
            session.removeAttribute("loggedUser");
            session.invalidate();
            log.info(String.format("User with email '%s' was signed out successfully! Session was destroyed!", user.getEmail()));
        }
    }

    public static Role getActiveRole(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedUser");

        return user != null ? Role.valueOf(user.getRole()) : Role.ROLE_GUEST;
    }
}
