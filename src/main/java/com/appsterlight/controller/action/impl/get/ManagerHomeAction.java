package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.model.domain.UI.UIController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManagerHomeAction extends FrontAction {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        UIController ui = new UIController(req);
        ui.init();

        String headerInfo = "Manager Home";
        req.setAttribute("HeaderInfo", headerInfo);
        return PagesNames.PAGE_MANAGER_HOME;
    }

}
