package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.model.db.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;

public class MySqlDaoFactory extends DaoFactory {
    private UserDao userDao;
    private ApartmentDao apartmentDao;
    private ApartmentClassDao apartmentClassDao;
    private BookingDao bookingDao;
    private OfferedApartmentsDao offeredApartmentsDao;
    private ApartmentPhotosDao apartmentPhotosDao;
    private ApartmentTagsDao apartmentTagsDao;

    private final DataSource dataSource;


    public MySqlDaoFactory(DataSource dataSource) {
        this.dataSource= dataSource;
    }

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new MySqlUserDao(dataSource);
        }

        return userDao;
    }

    @Override
    public ApartmentDao getApartmentDao() {
        if (apartmentDao == null) {
            apartmentDao = new MySqlApartmentDao(dataSource);
        }

        return apartmentDao;
    }

    @Override
    public ApartmentClassDao getApartmentClassDao() {
        if (apartmentClassDao == null) {
            apartmentClassDao = new MySqlApartmentClassDao(dataSource);
        }

        return apartmentClassDao;
    }

    @Override
    public BookingDao getBookingDao() {
        if (bookingDao == null) {
            bookingDao = new MySqlBookingDao(dataSource);
        }

        return bookingDao;
    }

    @Override
    public OfferedApartmentsDao getOfferedApartmentsDao() {
        if (offeredApartmentsDao == null) {
            offeredApartmentsDao = new MySqlOfferedApartmentDao(dataSource);
        }

        return offeredApartmentsDao;
    }

    @Override
    public ApartmentPhotosDao getApartmentPhotosDao() {
        if (apartmentPhotosDao == null) {
            apartmentPhotosDao = new MySqlApartmentPhotosDao(dataSource);
        }

        return apartmentPhotosDao;
    }

    @Override
    public ApartmentTagsDao getApartmentTagsDao() {
        if (apartmentTagsDao == null) {
            apartmentTagsDao = new MySqlApartmentTagsDao(dataSource);
        }

        return apartmentTagsDao;
    }
}
