package com.appsterlight.controller.action.impl.post;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.SessionUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Role;
import com.appsterlight.model.domain.User;
import com.appsterlight.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistrationAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        final String password = req.getParameter("password");
        final String confirmation = req.getParameter("confirm");

        if (password == null || confirmation == null) return PagesNames.PAGE_REGISTRATION;

        if (password.equals(confirmation)) {
            try {
                User user = createUserFromRequest(req);
                return ResisterUserWithSignIn(user, req) ? PagesNames.PAGE_START : PagesNames.PAGE_REGISTRATION;
            } catch (ServiceException e) {
                session.setAttribute("error", "Can't create user!");
                log.error(String.format("Error adding user into database while registration process! %s", e.getMessage()));
                return PagesNames.PAGE_REGISTRATION;
            }
        } else {
            session.setAttribute("error", "Password mismatch!");
            return PagesNames.PAGE_REGISTRATION;
        }
    }

    public boolean ResisterUserWithSignIn(User user, HttpServletRequest req) throws ServiceException{
        UserService userService = appContext.getUserService();
        if (!userService.isUserExists(user.getEmail())) {
            userService.addUser(user);
            HttpSession newSession = req.getSession();
            log.info(String.format("* User with email %s was successfully created", user.getEmail()));
            SessionUtils.SignInUserIntoSession(user, newSession);

            return true;
        } else {
            req.getSession().setAttribute("error", "User already exists!");

            return false;
        }
    }
    public static User createUserFromRequest(HttpServletRequest req) {
        final String firstname = req.getParameter("firstname");
        final String lastname = req.getParameter("lastname");
        final String email = req.getParameter("email");
        final String phoneNumber = req.getParameter("phone_number");
        final String password = req.getParameter("password");
        final String role = Role.ROLE_USER.toString();

        return User.builder()
                .firstName(firstname)
                .lastName(lastname)
                .email(email)
                .phoneNumber(phoneNumber)
                .password(password)
                .role(role)
                .build();
    }

}