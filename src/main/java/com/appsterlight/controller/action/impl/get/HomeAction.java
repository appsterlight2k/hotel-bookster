package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.ControllerUtils;
import com.appsterlight.controller.action.utils.SessionUtils;
import com.appsterlight.model.domain.UI.UIController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeAction extends FrontAction {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        UIController ui = new UIController(req);
        ui.init();

        return ControllerUtils.getHomePageByRole(SessionUtils.getActiveRole(req));
    }

}
