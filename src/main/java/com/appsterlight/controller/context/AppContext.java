package com.appsterlight.controller.context;

import com.appsterlight.model.db.connection.MySQLDBManager;
import com.appsterlight.model.db.dao.DaoFactory;
import com.appsterlight.model.db.dao.mysql.MySqlDaoFactory;
import com.appsterlight.service.ApartmentService;
import com.appsterlight.service.BookingService;
import com.appsterlight.service.ServiceFactory;
import com.appsterlight.service.UserService;
import jakarta.servlet.ServletContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class AppContext {
    private static AppContext instance;
    private final UserService userService;
    private final BookingService bookingService;
    private final ApartmentService apartmentService;

    private AppContext() {
        MySQLDBManager manager = MySQLDBManager.getInstance();
//        DaoFactory daoFactory = DaoFactory.getInstance();
        MySqlDaoFactory daoFactory = new MySqlDaoFactory(manager.getConnection());
        ServiceFactory serviceFactory = ServiceFactory.getInstance(daoFactory);
        userService = serviceFactory.getUserService();
        bookingService = serviceFactory.getBookingService();
        apartmentService = serviceFactory.getApartmentService();
    }

    public static AppContext getAppContext() {
        return instance;
    }

    public static void createAppContext() {
        instance = new AppContext();
    }


}
