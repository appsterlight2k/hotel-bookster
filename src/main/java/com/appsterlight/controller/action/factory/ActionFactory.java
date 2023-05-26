package com.appsterlight.controller.action.factory;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.impl.get.*;
import com.appsterlight.controller.action.impl.post.LoginAction;
import com.appsterlight.controller.action.impl.get.ManagerBookedAction;
import com.appsterlight.controller.action.impl.post.RegistrationAction;
import static com.appsterlight.controller.action.factory.constants.ActionName.*;

import com.appsterlight.controller.action.impl.get.ManagerRequestsAction;
import com.appsterlight.controller.action.impl.get.ShowApartmentAction;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ActionFactory {
    @Getter
    private static final ActionFactory actionFactory = new ActionFactory();
    private static final Map<String, FrontAction> ACTIONS = new HashMap<>();

    static {
        ACTIONS.put(ACTION_BOOKING, new BookingAction());
        ACTIONS.put(ACTION_HOME, new HomeAction());
        ACTIONS.put(ACTION_LOGIN, new LoginAction());
        ACTIONS.put(ACTION_LOGOUT, new LogoutAction());
        ACTIONS.put(ACTION_REGISTRATION, new RegistrationAction());
        ACTIONS.put(ACTION_MANAGER_HOME, new ManagerHomeAction());

        ACTIONS.put(ACTION_MANAGER_ALL_REQUESTS, new ManagerRequestsAction());
        ACTIONS.put(ACTION_MANAGER_BOOKING_REQUESTS, new ManagerBookingRequestsAction(true));
        ACTIONS.put(ACTION_MANAGER_REQUESTS_FOR_BOOKING, new ManagerBookingRequestsAction(false));

        ACTIONS.put(ACTION_MANAGER_OFFER_APARTMENTS, new ManagerOfferApartmentsAction());
        ACTIONS.put(ACTION_MANAGER_BOOKED, new ManagerBookedAction());

        ACTIONS.put(ACTION_APARTMENTS, new ApartmentsAction());
        ACTIONS.put(ACTION_GET_APARTMENT, new ShowApartmentAction());
        ACTIONS.put(ACTION_CABINET, new CabinetAction());
        ACTIONS.put(ACTION_ERROR, new ErrorAction());
        ACTIONS.put(ACTION_DEFAULT, new DefaultAction());
    }
    public static FrontAction getAction(HttpServletRequest req) {
        String action = req.getParameter("action");

        return getActionByName(action);
    }

    public static FrontAction getActionByName(String action) {
        return ACTIONS.getOrDefault(action, ACTIONS.get(ACTION_DEFAULT));
    }
}
