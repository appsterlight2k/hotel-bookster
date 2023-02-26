package com.appsterlight.service;

import com.appsterlight.model.db.dao.DaoFactory;
import com.appsterlight.service.impl.ApartmentServiceImpl;
import com.appsterlight.service.impl.BookingServiceImpl;
import com.appsterlight.service.impl.UserServiceImpl;
import lombok.Getter;

@Getter
public class ServiceFactory {
    private final UserService userService;
    private final BookingService bookingService;
    private final ApartmentService apartmentService;

    private ServiceFactory(DaoFactory daoFactory) {
        this.userService = new UserServiceImpl(daoFactory.getUserDao());
        this.bookingService = new BookingServiceImpl(daoFactory.getBookingDao());
        this.apartmentService = new ApartmentServiceImpl(daoFactory.getApartmentDao());

    }
    public static ServiceFactory getInstance(DaoFactory daoFactory) {
        return new ServiceFactory(daoFactory);
    }

    /*public UserService getUserService() {
        return userService;
    }*/

}
