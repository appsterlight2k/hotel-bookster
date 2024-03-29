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

    public List<Apartment> getAllFreeApartments(Integer guests, LocalDate checkIn, LocalDate checkOut)
            throws ServiceException {
        try {
            return apartmentDao.getAllFreeApartments(guests, checkIn, checkOut);
        } catch (DaoException e) {
            log.error("Can't get all free Apartments by guests number, checkin and checkout date. " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Apartment> getAllFreeApartments(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId)
            throws ServiceException {
        try {
            return apartmentDao.getAllFreeApartments(guests, checkIn, checkOut, classId);
        } catch (DaoException e) {
            log.error("Can't get all free Apartments by guests number, checkin-checkout date and class. " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Apartment> getAllFreeApartments(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer offset, Integer pagesCount) throws ServiceException {
        try {
            return apartmentDao.getAllFreeApartments(guests, checkIn, checkOut, offset, pagesCount);
        } catch (DaoException e) {
            log.error("Can't get all free Apartments by guests number, checkin-checkout date, offset and pages count. "
                    + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Apartment> getAllFreeApartments(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId,
                                                Integer offset, Integer pagesCount) throws ServiceException {
        try {
            return apartmentDao.getAllFreeApartments(guests, checkIn, checkOut, classId, offset, pagesCount);
        } catch (DaoException e) {
            log.error("Can't get all free Apartments by guests number, checkin-checkout date, class, offset and pages count. "
                    + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfAllFree(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId)
            throws ServiceException {
        try {
            return apartmentDao.getCountOfAllFree(guests, checkIn, checkOut, classId);
        } catch (DaoException e) {
            log.error("Can't get count of all free Apartments by guests number, checkin-checkout date and class. " +
                    e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfAllFree(Integer guests, LocalDate checkIn, LocalDate checkOut) throws ServiceException {
        try {
            return apartmentDao.getCountOfAllFree(guests, checkIn, checkOut);
        } catch (DaoException e) {
            log.error("Can't get count of all free Apartments by guests number, checkin-checkout date and class. " +
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
