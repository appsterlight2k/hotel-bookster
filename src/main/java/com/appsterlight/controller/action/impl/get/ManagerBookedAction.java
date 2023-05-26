package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.model.domain.UI.UIController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class ManagerBookedAction extends FrontAction {
    private final String headerInfo = "Booked Apartments List";

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        //process requests logic


        UIController ui = new UIController(req);
        ui.init();

        req.setAttribute("HeaderInfo", headerInfo);

        return PagesNames.PAGE_MANAGER_BOOKED;
    }
}
