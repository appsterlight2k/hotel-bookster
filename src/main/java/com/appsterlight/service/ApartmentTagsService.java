package com.appsterlight.service;

import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Tag;

import java.util.List;

public interface ApartmentTagsService {
    List<Tag> getAllTagsByApartmentId(Long id) throws ServiceException;
    List<Tag> getAllTagsByApartmentId(Long id, Boolean isBasic) throws ServiceException;

}
