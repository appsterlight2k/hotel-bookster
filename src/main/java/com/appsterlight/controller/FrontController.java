package com.appsterlight.controller;

import com.appsterlight.controller.command.FrontCommand;
import com.appsterlight.controller.command.factory.CommandFactory;
import com.appsterlight.exception.DispatcherException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/controller")
@Slf4j
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = null;
        try {
            forward = handleRequest(req, resp);
        } catch (DispatcherException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String redirect = null;
        try {
            redirect = handleRequest(req, resp);
        } catch (DispatcherException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(redirect);
    }

    private String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws DispatcherException {
        FrontCommand command = CommandFactory.getCommand(req);

        return command.process(req, resp);
    }

}
