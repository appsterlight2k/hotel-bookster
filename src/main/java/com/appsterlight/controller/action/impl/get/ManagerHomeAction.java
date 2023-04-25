package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManagerHomeAction extends FrontAction {
private final String headerInfo = "Manager Home";
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {


        req.setAttribute("HeaderInfo", headerInfo);
        return PagesNames.PAGE_MANAGER_HOME;
    }

}
