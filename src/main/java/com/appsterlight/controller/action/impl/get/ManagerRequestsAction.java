package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//All kind of Requests: Request for Booking,and Booking Request
public class ManagerRequestsAction extends FrontAction {
    private final String headerInfo = "Requests for Booking";
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {


        req.setAttribute("action", "requests-for-booking");
        req.setAttribute("HeaderInfo", headerInfo);

        //process requests logic
        return PagesNames.PAGE_MANAGER_REQUESTS;
    }
}
