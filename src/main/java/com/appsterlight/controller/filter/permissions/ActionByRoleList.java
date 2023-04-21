package com.appsterlight.controller.filter.permissions;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.factory.ActionFactory;
import com.appsterlight.model.domain.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.appsterlight.controller.action.factory.constants.ActionName.*;
import static com.appsterlight.controller.action.factory.constants.ActionName.ACTION_ERROR;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ActionByRoleList {
    @Getter
    private static final List<String> guestActions = new ArrayList<>();
    @Getter
    private static final List<String> userActions = new ArrayList<>();
    @Getter
    private static final List<String> managerActions = new ArrayList<>();

    static {
        //Guest actions:
        guestActions.addAll(Arrays.asList(
            ACTION_HOME,
            ACTION_LOGIN,
            ACTION_LOGOUT,
            ACTION_REGISTRATION,
            ACTION_APARTMENTS,
            ACTION_GET_APARTMENT,
            ACTION_ERROR
        ));

        //User actions:
        userActions.addAll(Arrays.asList(
            ACTION_HOME,
            ACTION_LOGIN,
            ACTION_LOGOUT,
            ACTION_REGISTRATION,
            ACTION_APARTMENTS,
            ACTION_GET_APARTMENT,
            ACTION_BOOKING,
            ACTION_CABINET,
            ACTION_ERROR
        ));

        //Manager actions:
        managerActions.addAll(Arrays.asList(
            ACTION_LOGIN,
            ACTION_LOGOUT,
            ACTION_MANAGER_HOME,

            ACTION_MANAGER_ALL_APARTMENTS,
            ACTION_MANAGER_ALL_REQUESTS,
            ACTION_MANAGER_REQUESTS_FOR_BOOKING,
            ACTION_MANAGER_BOOKING_REQUESTS,
            ACTION_MANAGER_OFFER_APARTMENTS,
            ACTION_MANAGER_BOOKED,

            ACTION_APARTMENTS,  //choose apartments for request
            ACTION_ERROR
        ));
    }

    public static FrontAction getActionByRole(String action, String role) {
        String result;
        try {
            /*result = switch (Role.get(role)) {
                case ROLE_GUEST -> guestActions.contains(action) ? action : ACTION_ERROR;
                case ROLE_USER -> userActions.contains(action) ? action : ACTION_ERROR;
                case ROLE_MANAGER -> managerActions.contains(action) ? action : ACTION_ERROR;
            };*/

            Role userRole = Role.valueOf(role);
            if (userRole == Role.ROLE_GUEST) {
                result = guestActions.contains(action) ? action : ACTION_ERROR;
            } else if (userRole == Role.ROLE_USER) {
                result = userActions.contains(action) ? action : ACTION_ERROR;
            } else if (userRole == Role.ROLE_MANAGER) {
                result = managerActions.contains(action) ? action : ACTION_ERROR;
            } else {
                result = ACTION_ERROR;
            }
        } catch (Exception e) {
            result = ACTION_ERROR;
            log.error("Action is null! -> " + e.getMessage());
        }

        return ActionFactory.getActionByName(result);
    }


}
