package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.ControllerUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class HomeAction extends FrontAction {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        final HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedUser");

        return  user != null ?
                ControllerUtils.getHomePageByRole(user.getRole()) :
                PagesNames.PAGE_START;

    }

}
