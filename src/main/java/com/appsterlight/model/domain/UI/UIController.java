package com.appsterlight.model.domain.UI;

import com.appsterlight.controller.action.utils.SessionUtils;
import com.appsterlight.model.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UIController {
private TabMenu tabMenu;
private Searcher searcher;
private Pagination pagination;
private final HttpServletRequest req;

    public UIController(HttpServletRequest req) {
        this.req = req;
    }

    public void init() {
        TabMenu tabMenu = new TabMenu(req);
        if (SessionUtils.getActiveRole(req) == Role.ROLE_MANAGER) {
            tabMenu.setUIControls();
        }
    }


}
