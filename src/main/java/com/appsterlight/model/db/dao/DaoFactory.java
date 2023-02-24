package com.appsterlight.model.db.dao;

import com.appsterlight.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

import static com.appsterlight.model.db.constants.Messages.DAO_FACTORY_INSTANCE_CREATION_ERROR;

@Slf4j
public abstract class DaoFactory {
    private static DaoFactory instance;

    protected DaoFactory() { }

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
//           instance = new MySqlDaoFactory(con);
            Class<?> cl = null;
            try {
                cl = Class.forName(DaoFactory.class.getName());
                instance = (DaoFactory) cl.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                log.error(DAO_FACTORY_INSTANCE_CREATION_ERROR, e.getMessage());
            }
        }

        return instance;
    }

    public abstract UserDao getUserDao();
    public abstract ApartmentDao getApartmentDao();
    public abstract BookingDao getBookingDao();
    public abstract ApartmentPhotosDao getApartmentPhotosDao();
}
