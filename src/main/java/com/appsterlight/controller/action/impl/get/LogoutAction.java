package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutAction extends FrontAction {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        UserDto user = (UserDto) session.getAttribute("loggedUser");
        if (user != null) {
            session.removeAttribute("loggedUser");
            session.invalidate();
            log.info(String.format("User with email '%s' was signed out successfully!", user.getEmail()));
        }

        return PagesNames.PAGE_START;
    }
}