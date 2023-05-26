package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.model.domain.UI.UIController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CabinetAction extends FrontAction {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {

        UIController ui = new UIController(req);
        ui.init();

        //process cabinet logic
        return PagesNames.PAGE_CABINET;
    }

}
