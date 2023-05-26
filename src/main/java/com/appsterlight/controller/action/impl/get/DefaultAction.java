package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DefaultAction extends FrontAction {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        return PagesNames.PAGE_START;
    }
}
