package com.appsterlight.controller.action.utils;

import com.appsterlight.exception.ServiceException;
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
        session.setAttribute("loggedUser", DtoUtils.mapUserToDto(Optional.of(user)));
        session.setAttribute("error", "");
        log.info(String.format("* User with email %s logged in successfully!", user.getEmail()));
    }

    public static void ShutDownSession(HttpServletRequest req) {
        req.getSession().invalidate();
        log.info("Session was destroyed!");
    }


}
