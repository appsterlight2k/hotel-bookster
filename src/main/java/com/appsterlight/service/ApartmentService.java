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

    List<Apartment> getAllFreeApartments(Integer guests, LocalDate checkIn, LocalDate checkOut) throws ServiceException;
    List<Apartment> getAllFreeApartmentsByClass(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId) throws ServiceException;




}
