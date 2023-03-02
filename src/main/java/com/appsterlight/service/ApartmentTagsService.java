package com.appsterlight.service;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.model.domain.Tag;

import java.util.List;
import java.util.Optional;

public interface ApartmentTagsService {
    List<Tag> getAllTagsByApartmentId(Long id) throws ServiceException;

}
