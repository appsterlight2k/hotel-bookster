package com.appsterlight.controller.action;

import com.appsterlight.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class FrontAction {
    public abstract String process(HttpServletRequest req, HttpServletResponse resp);
}
