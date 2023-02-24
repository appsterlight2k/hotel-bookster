package com.appsterlight.controller.action;

import com.appsterlight.controller.action.impl.get.ErrorAction;
import com.appsterlight.controller.action.impl.get.HomeAction;

public enum ActionEnum {
    HOME(new HomeAction()), ERROR(new ErrorAction());

    private FrontAction action;

    ActionEnum(FrontAction action) {
        this.action = action;
    }

    public FrontAction getAction() {
        return action;
    }

}
