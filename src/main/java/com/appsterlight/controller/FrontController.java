package com.appsterlight.controller;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.factory.ActionFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import static com.appsterlight.controller.action.utils.ControllerUtils.isGetMethod;
import static com.appsterlight.controller.constants.PagesNames.JSON_RESPONSE;

@WebServlet(name = "FrontController", value = "/controller")
@Slf4j
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        FrontAction action = ActionFactory.getAction(req);

        try {
            String target = action.process(req, resp);
           if (isGetMethod(req)) {
               RequestDispatcher view = req.getRequestDispatcher(target);
               view.forward(req, resp);
           } else {
               if (!target.equals(JSON_RESPONSE)) resp.sendRedirect(target);
           }
        } catch (Exception e) {
            log.error("Exception in handleRequest method: Can't handle request! " + e);
        }
    }

}
