package com.appsterlight.controller.action.impl.post;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.SessionUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.exception.NoSuchUserException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.UI.UIController;
import com.appsterlight.model.domain.User;
import com.appsterlight.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import static com.appsterlight.controller.action.utils.ControllerUtils.getHomePageByRole;
import static com.appsterlight.controller.action.utils.SessionUtils.getActiveRole;

@Slf4j
public class LoginAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        UIController ui = new UIController(req);

        //reg process
        final String email = req.getParameter("email");
        final String password = req.getParameter("pass");

        if (email == null || password == null) {
            return PagesNames.PAGE_LOGIN;
        }

        UserService userService = appContext.getUserService();
        try {
            User user = userService.getUserByEmail(email).orElseThrow(NoSuchUserException::new);
            if (user.getPassword().equals(password)) {
                SessionUtils.SignInUserIntoSession(user, session);
                ui.init();

                return getHomePageByRole(getActiveRole(req)) + "?menuHome=active";
            } else {
                session.setAttribute("error", "Incorrect Password");
                log.info(String.format("Incorrect password for user with email %s", email));
            }
        } catch (NoSuchUserException e) {
            session.setAttribute("error", "No such user");
            log.error(String.format("* User with email %s doesn't exist", email) + "! " + e.getMessage());
        }

        return PagesNames.PAGE_LOGIN;
    }
}