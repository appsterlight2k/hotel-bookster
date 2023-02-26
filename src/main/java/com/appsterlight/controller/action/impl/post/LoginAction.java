package com.appsterlight.controller.action.impl.post;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.SessionUtils;
import com.appsterlight.controller.action.utils.UserUtils;
import com.appsterlight.controller.context.AppContext;
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
public class LoginAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        if (session.getAttribute("loggedUser") != null) {
            return "index.jsp";
        }

        final String email = req.getParameter("email");
        final String password = req.getParameter("pass");

        UserService userService = appContext.getUserService();
        try {
            User user = userService.getUserByEmail(email).orElseThrow(NoSuchUserException::new);

            if (user.getPassword().equals(password)) {
                SessionUtils.SignInUserIntoSession(user, session);
                return "index.jsp";
            } else {
                session.setAttribute("error", "Incorrect Password");
                log.info(String.format("Incorrect password for user with email %s", email));
            }
        } catch (ServiceException e) {
            session.setAttribute("error", "No such user");
            log.error(String.format("* User with email %s doesn't exist", email));
        }
        return "login.jsp";
    }

  /*  private void SignInUserIntoSession(User user, HttpSession session) throws ServiceException {
        UserService userService = appContext.getUserService();
        session.setAttribute("loggedUser", UserUtils.mapUserToDto(Optional.of(user)));
        session.setAttribute("error", "");
        log.info(String.format("* User with email %s logged in successfully!", user.getEmail()));
    }*/
}