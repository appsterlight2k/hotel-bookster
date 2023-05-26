package com.appsterlight.service;

import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Apartment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApartmentService {
    Long addApartment(Apartment apartment) throws ServiceException;
    Optional<Apartment> getApartmentById(Long id) throws ServiceException;
    boolean updateApartment(Apartment apartment) throws ServiceException;
    boolean deleteApartment(Long id) throws ServiceException;
    List<Apartment> getAllApartments() throws ServiceException;

    List<Apartment> getAllApartments(Integer guests, LocalDate checkIn, LocalDate checkOut,
                                         Integer classId, String status, String sortingField,
                                         String sortingOrder, Integer offset, Integer pageSize) throws ServiceException;

    Integer getCountOfAllApartments(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId,
                              String status) throws ServiceException;
}
