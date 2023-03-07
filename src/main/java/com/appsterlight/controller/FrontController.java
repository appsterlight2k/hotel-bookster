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

@WebServlet(name = "FrontController", value = "/controller")
@Slf4j
public class FrontController extends HttpServlet {
    private int counter;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">> doGet() -> " + ++counter);
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(">> doPost() -> " + ++counter);
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        FrontAction action = ActionFactory.getAction(req);
        String target = action.process(req, resp);

        String method = req.getMethod();
        try {
           /*if (method.equalsIgnoreCase("POST")) {
                   resp.sendRedirect(target);*/
           /*} else {*/
               req.getRequestDispatcher(target).forward(req, resp);

           /*}*/
        } catch (IOException | ServletException e) {
            log.error("Exception in handleRequest method -> " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
