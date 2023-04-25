package com.appsterlight.model.db.dao;

import com.appsterlight.exception.DaoException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.shared.Dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApartmentDao extends Dao<Apartment> {
    Optional<Apartment> getApartmentByApartmentNumber(String number) throws DaoException;

    List<Apartment> getAllApartments(Integer guests, LocalDate checkIn, LocalDate checkOut,
                                                Integer classId, String status, String sortingField,
                                                String sortingOrder, Integer offset, Integer pageSize) throws DaoException;

    Integer getCountOfAllApartments(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId,
                              String status) throws DaoException;

}