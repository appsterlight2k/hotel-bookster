package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.model.db.dao.*;

import java.sql.Connection;

public class MySqlDaoFactory extends DaoFactory {
    private UserDao userDao;
    private ApartmentDao apartmentDao;
    private BookingDao bookingDao;
    private ApartmentPhotosDao apartmentPhotosDao;

    private Connection con;


    public MySqlDaoFactory(Connection con) {
        this.con = con;
    }

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new MySqlUserDao(con);
        }

        return userDao;
    }

    @Override
    public ApartmentDao getApartmentDao() {
        if (apartmentDao == null) {
            apartmentDao = new MySqlApartmentDao(con);
        }

        return apartmentDao;
    }

    @Override
    public BookingDao getBookingDao() {
        if (bookingDao == null) {
            bookingDao = new MySqlBookingDao(con);
        }

        return bookingDao;
    }

    @Override
    public ApartmentPhotosDao getApartmentPhotosDao() {
        if (apartmentPhotosDao == null) {
            apartmentPhotosDao = new MySqlApartmentPhotosDao(con);
        }

        return apartmentPhotosDao;
    }
}
