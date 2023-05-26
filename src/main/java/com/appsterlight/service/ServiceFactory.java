package com.appsterlight.service;

import com.appsterlight.model.db.dao.DaoFactory;
import com.appsterlight.service.impl.*;
import lombok.Getter;

@Getter
public class ServiceFactory {
    private final UserService userService;
    private final BookingService bookingService;
    private final OfferedApartmentsService offeredApartmentsService;
    private final ApartmentService apartmentService;
    private final ApartmentClassService apartmentClassService;
    private final ApartmentPhotosService apartmentPhotosService;
    private final ApartmentTagsService apartmentTagsService;

    private ServiceFactory(DaoFactory daoFactory) {
        this.userService = new UserServiceImpl(daoFactory.getUserDao());
        this.bookingService = new BookingServiceImpl(daoFactory.getBookingDao());
        this.offeredApartmentsService = new OfferedApartmentsServiceImpl(daoFactory.getOfferedApartmentsDao());
        this.apartmentService = new ApartmentServiceImpl(daoFactory.getApartmentDao());
        this.apartmentClassService = new ApartmentClassServiceImpl(daoFactory.getApartmentClassDao());
        this.apartmentPhotosService = new ApartmentPhotosServiceImpl(daoFactory.getApartmentPhotosDao());
        this.apartmentTagsService = new ApartmentTagsServiceImpl(daoFactory.getApartmentTagsDao());
    }
    public static ServiceFactory getInstance(DaoFactory daoFactory) {
        return new ServiceFactory(daoFactory);
    }

}
