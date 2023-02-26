package com.appsterlight.controller.action.impl.post;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.NoSuchUserException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.User;
import com.appsterlight.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

//@RequiredArgsConstructor
@Slf4j
public class LogoutAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        UserDto user = (UserDto) session.getAttribute("loggedUser");
        if (user != null) {
            session.invalidate();
            log.info(String.format("User with email '%s' was signed out successfully!", user.getEmail()));
        }

        return "index.jsp";
    }
}