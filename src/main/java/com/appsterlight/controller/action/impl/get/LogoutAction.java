package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import static com.appsterlight.controller.action.utils.SessionUtils.ShutDownSession;

@Slf4j
public class LogoutAction extends FrontAction {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ShutDownSession(session);

        return PagesNames.PAGE_START;
    }
}