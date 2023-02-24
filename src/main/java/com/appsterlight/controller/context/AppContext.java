package com.appsterlight.controller.context;

import com.appsterlight.model.db.connection.MySQLDBManager;
import com.appsterlight.model.db.dao.DaoFactory;
import jakarta.servlet.ServletContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class AppContext {
    private static AppContext instance;

    private AppContext(ServletContext servletContext) {
//        MySQLDBManager bBManager = MySQLDBManager.getInstance();
//        DaoFactory daoFactory = DaoFactory.getInstance();


    }

    public static AppContext getAppContext() {
        return instance;
    }

    public static void createAppContext(ServletContext servletContext) {
        instance = new AppContext(servletContext);
    }


}
