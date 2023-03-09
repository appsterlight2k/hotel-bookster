package com.appsterlight.service;

import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.ApartmentClass;

import java.util.List;
import java.util.Optional;

public interface ApartmentClassService {
    Long addApartmentClass(ApartmentClass apartmentClass) throws ServiceException;
    Optional<ApartmentClass> getApartmentClassById(Long id) throws ServiceException;
    boolean updateApartmentClass(ApartmentClass apartmentClass) throws ServiceException;
    boolean deleteApartmentClass(Long id) throws ServiceException;
    List<ApartmentClass> getAllApartmentClasses() throws ServiceException;





}
