package com.appsterlight.controller.listener;

import com.appsterlight.controller.context.AppContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("The Application was started");
        try {
            AppContext.createAppContext(sce.getServletContext());
        } catch (Exception e) {
            log.error("Can't create AppContext", e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("The Application has successfully stopped");
    }
}
