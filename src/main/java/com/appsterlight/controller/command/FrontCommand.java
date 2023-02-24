package com.appsterlight.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class FrontCommand {
    public abstract String process(HttpServletRequest req, HttpServletResponse resp);
}
