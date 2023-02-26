package com.appsterlight.controller;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.factory.ActionFactory;
import com.appsterlight.exception.FrontControllerException;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import com.appsterlight.utils.ConnectionUtils.*;

@WebServlet("/controller")
@Slf4j
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(handleRequest(req, resp)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(handleRequest(req, resp));
    }

    private String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        FrontAction action = ActionFactory.getAction(req);

        return action.process(req, resp);
    }

}
