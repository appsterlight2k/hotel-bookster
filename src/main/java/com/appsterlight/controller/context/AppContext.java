package com.appsterlight.controller.context;

import com.appsterlight.model.db.connection.MySQLDBManager;
import com.appsterlight.model.db.dao.mysql.MySqlDaoFactory;
import com.appsterlight.service.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class AppContext {
    private static AppContext instance;
    private final UserService userService;
    private final BookingService bookingService;
    private final ApartmentService apartmentService;
    private final ApartmentClassService apartmentClassService;
    private final ApartmentPhotosService apartmentPhotosService;
    private final ApartmentTagsService apartmentTagsService;

    private AppContext() {
        MySQLDBManager manager = MySQLDBManager.getInstance();
        MySqlDaoFactory daoFactory = new MySqlDaoFactory(manager.getDataSource());
        ServiceFactory serviceFactory = ServiceFactory.getInstance(daoFactory);
        userService = serviceFactory.getUserService();
        bookingService = serviceFactory.getBookingService();
        apartmentService = serviceFactory.getApartmentService();
        apartmentClassService = serviceFactory.getApartmentClassService();
        apartmentPhotosService = serviceFactory.getApartmentPhotosService();
        apartmentTagsService = serviceFactory.getApartmentTagsService();
    }

    public static AppContext getAppContext() {
        return instance;
    }

    public static void createAppContext() {
        instance = new AppContext();
    }


}
