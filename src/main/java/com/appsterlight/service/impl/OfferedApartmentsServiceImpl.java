package com.appsterlight.service.impl;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.db.dao.OfferedApartmentsDao;
import com.appsterlight.model.domain.OfferedAparment;
import com.appsterlight.service.OfferedApartmentsService;
import com.appsterlight.shared.Dao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OfferedApartmentsServiceImpl implements OfferedApartmentsService {
    private final OfferedApartmentsDao offeredApartmentsDao;

    @Override
    public Long add(OfferedAparment apartment) throws ServiceException {
        try {
            return offeredApartmentsDao.add(apartment);
        } catch (DaoException e) {
            log.error("Can't add an Apartment into table offered_apartments! " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OfferedAparment> getAllOfferedApartmentsByBookingId(Long id) throws ServiceException {
        try {
            return offeredApartmentsDao.getAllOfferedApartmentsByBookingId(id);
        } catch (DaoException e) {
            log.error("Can't get Offered Apartments from table by booking id! " + e);
            throw new ServiceException("Can't get Offered Apartments from table by booking id! " + e);
        }
    }
}
