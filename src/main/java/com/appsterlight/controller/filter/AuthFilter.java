package com.appsterlight.controller.filter;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.factory.ActionFactory;
import com.appsterlight.controller.action.utils.ControllerUtils;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.controller.filter.permissions.ActionByRoleList;
import com.appsterlight.model.domain.Role;
import com.appsterlight.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.appsterlight.controller.action.factory.constants.ActionName.ACTION_ERROR;

@Slf4j
@WebFilter("/controller")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final AppContext appContext = AppContext.getAppContext();
        final HttpSession session = req.getSession();

        String action = req.getParameter("action");
        final Role role;

        UserDto user = (UserDto) session.getAttribute("loggedUser");
        if (user != null) {
            role = Role.valueOf(user.getRole()); //USER, MANAGER
        } else {
            role = Role.ROLE_GUEST;
        }

        FrontAction actionByRole = ActionByRoleList.getActionByRole(action, role.toString());

        if (actionByRole == ActionFactory.getActionByName(ACTION_ERROR)) {
            //in case of access permissions violation:
            log.error("*** User have tried to access restricted domain. Violation of access permissions! ***");
            redirectToHome(req, res, role);
        } else {
            //regular case:
            chain.doFilter(request, response);
        }
    }

    private void redirectToHome(final HttpServletRequest req, final HttpServletResponse res, final Role role)
            throws ServletException, IOException {
        String target = ControllerUtils.getHomePageByRole(role.toString());
        req.getRequestDispatcher(target).forward(req, res);
    }

}
