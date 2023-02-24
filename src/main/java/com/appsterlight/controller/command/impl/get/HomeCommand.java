package com.appsterlight.controller.command.impl.get;

import com.appsterlight.controller.command.FrontCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeCommand extends FrontCommand {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("email", "my.test@mail.com");

        return "home.jsp";
    }

}
