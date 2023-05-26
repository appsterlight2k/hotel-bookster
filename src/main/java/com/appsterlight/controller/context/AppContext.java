package com.appsterlight.controller.context;

import com.appsterlight.model.db.connection.MyDataSource;
import com.appsterlight.model.db.dao.mysql.MySqlDaoFactory;
import com.appsterlight.service.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.appsterlight.model.db.constants.Constants.MAX_GUESTS_NUMBER;


@Slf4j
@Getter
public class AppContext {
    private static AppContext instance;
    private final UserService userService;
    private final BookingService bookingService;
    private final OfferedApartmentsService offeredApartmentsService;
    private final ApartmentService apartmentService;
    private final ApartmentClassService apartmentClassService;
    private final ApartmentPhotosService apartmentPhotosService;
    private final ApartmentTagsService apartmentTagsService;

    private Integer maxGuestsNumber;

    private AppContext() {
        MyDataSource manager = MyDataSource.getInstance();
        MySqlDaoFactory daoFactory = new MySqlDaoFactory(manager.getDataSource());
        ServiceFactory serviceFactory = ServiceFactory.getInstance(daoFactory);
        userService = serviceFactory.getUserService();
        bookingService = serviceFactory.getBookingService();
        offeredApartmentsService = serviceFactory.getOfferedApartmentsService();
        apartmentService = serviceFactory.getApartmentService();
        apartmentClassService = serviceFactory.getApartmentClassService();
        apartmentPhotosService = serviceFactory.getApartmentPhotosService();
        apartmentTagsService = serviceFactory.getApartmentTagsService();

        getAppSettings();
    }

    private void getAppSettings() {
        final String maxGuests = MyDataSource.getSettings().get(MAX_GUESTS_NUMBER);
        try {
            int maxGuestsValue = Integer.parseInt(maxGuests);
            this.maxGuestsNumber = maxGuestsValue > 0 ? maxGuestsValue : 1;
        } catch (NumberFormatException e) {
            log.error("Can't parse maxGuestsNumber App Settings!", e);
        }

    }

    public Integer getMaxGuestsNumber() {
        return maxGuestsNumber;
    }

    public static AppContext getAppContext() {
        return instance;
    }

    public static void createAppContext() {
        instance = new AppContext();
    }


}
