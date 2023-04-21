package com.appsterlight.service.impl;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.db.dao.ApartmentClassDao;
import com.appsterlight.model.domain.ApartmentClass;
import com.appsterlight.service.ApartmentClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ApartmentClassServiceImpl implements ApartmentClassService {
    private final ApartmentClassDao apartmentClassDao;

    @Override
    public Long addApartmentClass(ApartmentClass apartmentClass) throws ServiceException {
        try {
            return apartmentClassDao.add(apartmentClass);
        } catch (DaoException e) {
            log.error("Can't add Apartment class into table", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ApartmentClass> getApartmentClassById(Long id) throws ServiceException {
        try {
            return apartmentClassDao.get(id);
        } catch (DaoException e) {
            log.error("Can't get Apartment Class with id = " + id);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateApartmentClass(ApartmentClass apartmentClass) throws ServiceException {
        try {
            return apartmentClassDao.update(apartmentClass);
        } catch (DaoException e) {
            log.error("Can't update Apartment Class with id = " + apartmentClass.getId());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteApartmentClass(Long id) throws ServiceException {
        try {
            return apartmentClassDao.delete(id);
        } catch (DaoException e) {
            log.error("Can't delete Apartment Class with id = " + id);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ApartmentClass> getAllApartmentClasses() throws ServiceException {
        try {
            return apartmentClassDao.getAll();
        } catch (DaoException e) {
            log.error(String.format("Can't get all apartment from table. %s", e.getMessage()));
            throw new ServiceException(e);
        }
    }

}
