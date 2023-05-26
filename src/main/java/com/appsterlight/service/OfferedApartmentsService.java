package com.appsterlight.service;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.OfferedAparment;
import com.appsterlight.shared.Dao;

import java.util.List;

public interface OfferedApartmentsService {

    Long add(OfferedAparment apartment) throws ServiceException;
    List<OfferedAparment> getAllOfferedApartmentsByBookingId(Long id) throws ServiceException;
}
