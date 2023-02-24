package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ErrorAction extends FrontAction {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        return "error.jsp";
    }
}
