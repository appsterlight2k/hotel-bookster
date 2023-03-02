package com.appsterlight.controller.filter.permissions;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.factory.ActionFactory;
import com.appsterlight.model.domain.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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
        guestActions.add(ACTION_HOME);
        guestActions.add(ACTION_LOGIN);
        guestActions.add(ACTION_LOGOUT);
        guestActions.add(ACTION_REGISTRATION);
        guestActions.add(ACTION_APARTMENTS);
        guestActions.add(ACTION_ERROR);

        userActions.add(ACTION_HOME);
        userActions.add(ACTION_LOGIN);
        userActions.add(ACTION_LOGOUT);
        userActions.add(ACTION_REGISTRATION);
        userActions.add(ACTION_APARTMENTS);
        userActions.add(ACTION_GET_APARTMENT);

        userActions.add(ACTION_BOOKING);
        userActions.add(ACTION_CABINET);
        userActions.add(ACTION_ERROR);

        managerActions.add(ACTION_LOGIN);
        managerActions.add(ACTION_LOGOUT);
//        managerActions.add(ACTION_REGISTRATION);
        managerActions.add(ACTION_MANAGER_HOME);
        managerActions.add(ACTION_MANAGER_REQUESTS);
        managerActions.add(ACTION_APARTMENTS); //choose apartments for request
        managerActions.add(ACTION_ERROR);

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
