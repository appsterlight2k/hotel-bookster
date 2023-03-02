package com.appsterlight.controller.listener;

import com.appsterlight.controller.context.AppContext;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("The Application was started");
        try {
            AppContext.createAppContext();
            log.info("Application Context was created");
        } catch (Exception e) {
            log.error(String.format("Can't create Application Context! ", e.getMessage()));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AbandonedConnectionCleanupThread.checkedShutdown();
        disconnectDrivers();

        log.info("The Application has successfully stopped");
    }

    private static void disconnectDrivers() {
        DriverManager.drivers().forEach(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.error("Couldn't deregister " + driver);
            }
        });
    }

}
