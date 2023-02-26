package com.appsterlight.controller.action.factory;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.impl.get.ErrorAction;
import com.appsterlight.controller.action.impl.get.HomeAction;
import com.appsterlight.controller.action.impl.post.LoginAction;
import com.appsterlight.controller.action.impl.post.LogoutAction;
import com.appsterlight.controller.action.impl.post.RegisterAction;
import com.appsterlight.controller.context.AppContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ActionFactory {
    private static final AppContext appContext = AppContext.getAppContext();


    public static FrontAction getAction(HttpServletRequest req) {
        FrontAction action;
        String reqAction = req.getParameter("action");

        try {
            action = (reqAction != null) ?
                    ActionEnum.valueOf(reqAction.toUpperCase()).getAction() :
                    ActionEnum.ERROR.getAction();
        } catch (IllegalArgumentException e) {
            action = ActionEnum.ERROR.getAction();
            log.error(String.format("Unexpected action from request: %s", e.getMessage()));
        }

        return action;
    }

      enum ActionEnum {
        HOME(new HomeAction()),
        LOGIN(new LoginAction()),
        LOGOUT(new LogoutAction()),
        REGISTER(new RegisterAction()),
        DAFAULT(new HomeAction()),
        ERROR(new ErrorAction());

        private final FrontAction action;

        ActionEnum(FrontAction action) {
            this.action = action;
        }

        public FrontAction getAction() {
            return action;
        }

    }

}
