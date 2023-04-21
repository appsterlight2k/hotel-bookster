package com.appsterlight.controller.action.utils;

import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.model.domain.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ControllerUtils {
    public static String getHomePageByRole(String role) {
        try {
            Role actualRole = Role.valueOf(role);
            if (actualRole == Role.ROLE_MANAGER) {
                return PagesNames.PAGE_MANAGER_HOME;
            } else if (actualRole == Role.ROLE_USER) {
//                return PagesNames.PAGE_HOME_USER;
                return PagesNames.PAGE_START;
            } else {
//                return PagesNames.PAGE_HOME_GUEST;
                return PagesNames.PAGE_START;
            }
        } catch (IllegalArgumentException e) {
            log.error("Can't get home page for role! " + e.getMessage());
            return PagesNames.PAGE_START;
        }
    }
}
