package com.appsterlight.model.db.dao;

import com.appsterlight.exception.DaoException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.shared.Dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApartmentDao extends Dao<Apartment> {
    Optional<Apartment> getApartmentByApartmentNumber(String number) throws DaoException;
    List<Apartment> getAllFreeByGuestsNumber(Integer guests,
                                             LocalDate checkIn, LocalDate checkOut) throws DaoException;
    List<Apartment> getAllFreeByGuestsNumberAndClass(Integer guests,
                                             LocalDate checkIn, LocalDate checkOut, Integer classId) throws DaoException;
    Integer getCountOfAllFree(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId) throws DaoException;
}
