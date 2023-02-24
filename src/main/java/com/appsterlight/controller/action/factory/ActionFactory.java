package com.appsterlight.controller.action.factory;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.ActionEnum;
import com.appsterlight.exception.FrontControllerException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ActionFactory {
    private ActionFactory() { }

    public static FrontAction getAction(HttpServletRequest req) throws FrontControllerException {
        FrontAction action;
        String reqAction = req.getParameter("action");

        try {
            action = (reqAction != null) ? ActionEnum.valueOf(reqAction.toUpperCase()).getAction() :
                        ActionEnum.ERROR.getAction();
        } catch (IllegalArgumentException e) {
            action = ActionEnum.ERROR.getAction();
            log.error("Unexpected action from request", e.getMessage());
        }

        return action;
    }

}
