package com.appsterlight.service.impl;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.db.dao.ApartmentDao;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentDao apartmentDao;


    @Override
    public Long addApartment(Apartment apartment) throws ServiceException {
        try {
            return apartmentDao.add(apartment);
        } catch (DaoException e) {
            log.error("Can't add apartment into table", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Apartment> getApartmentById(Long id) throws ServiceException {
        try {
            return apartmentDao.get(id);
        } catch (DaoException e) {
            log.error(String.format("Can't get apartment with id = %s", id), e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateApartment(Apartment apartment) throws ServiceException {
        try {
            return apartmentDao.update(apartment);
        } catch (DaoException e) {
            log.error(String.format("Can't update apartment with id = %s", apartment.getId()), e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteApartment(Long id) throws ServiceException {
        try {
            return apartmentDao.delete(id);
        } catch (DaoException e) {
            log.error(String.format("Can't delete apartment with id = %s", id), e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Apartment> getAllApartments(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId,
                                                String status, String sortingField, String sortingOrder, Integer offset,
                                                Integer pageSize) throws ServiceException {
        try {
            return apartmentDao.getAllApartments(guests, checkIn, checkOut, classId, status, sortingField,
                    sortingOrder, offset, pageSize);
        } catch (DaoException e) {
            log.error("Can't get all Apartments by guests number, checkin-checkout date, class, status, sorting field, " +
                    "sorting order, offset and pages count. "
                    + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfAllApartments(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId,
                                     String status) throws ServiceException {
        try {
            return apartmentDao.getCountOfAllApartments(guests, checkIn, checkOut, classId, status);
        } catch (DaoException e) {
            log.error("Can't get count of all Apartments by guests number, checkin-checkout date, class, " +
                    "status, sortingField and sortingOrder!" +
                    e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Apartment> getAllApartments() throws ServiceException {
        try {
            return apartmentDao.getAll();
        } catch (DaoException e) {
            log.error(String.format("Can't get all apartment from table. %s", e.getMessage()));
            throw new ServiceException(e);
        }
    }
}
