package com.appsterlight.controller.action.impl.post;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestsAction extends FrontAction {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {

        //process requests logic
        return PagesNames.PAGE_MANAGER_REQUESTS;
    }
}
