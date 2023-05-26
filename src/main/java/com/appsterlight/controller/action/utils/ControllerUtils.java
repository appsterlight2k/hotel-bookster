package com.appsterlight.controller.action.utils;

import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.model.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ControllerUtils {

    public static boolean isGetMethod(HttpServletRequest req) {
        return req.getMethod().equalsIgnoreCase("GET");
    }

    public static String getHomePageByRole(Role role) {
        try {
            if (role == Role.ROLE_MANAGER) {
                return PagesNames.PAGE_MANAGER_HOME;
            } else if (role == Role.ROLE_USER) {
                return PagesNames.PAGE_START;
            } else {
                return PagesNames.PAGE_START;
            }
        } catch (IllegalArgumentException e) {
            log.error("Can't get home page for role! " + e.getMessage());
            return PagesNames.PAGE_START;
        }
    }
}
