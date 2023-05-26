package com.appsterlight.model.db.dao;


import com.appsterlight.exception.DaoException;
import com.appsterlight.model.domain.OfferedAparment;
import com.appsterlight.shared.Dao;

import java.util.List;


public interface OfferedApartmentsDao extends Dao<OfferedAparment> {
    List<OfferedAparment> getAllOfferedApartmentsByBookingId(Long id) throws DaoException;
}
