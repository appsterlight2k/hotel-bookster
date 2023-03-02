package com.appsterlight.model.db.dao;

import com.appsterlight.exception.DaoException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.shared.Dao;

import java.util.Optional;

public interface ApartmentDao extends Dao<Apartment> {
    Optional<Apartment> getApartmentByApartmentNumber(String number) throws DaoException;

}
