package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CabinetAction extends FrontAction {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {


        //process cabiner logic
        return PagesNames.PAGE_CABINET;
    }

}
