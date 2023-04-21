package com.appsterlight.service.impl;

import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.db.dao.ApartmentTagsDao;
import com.appsterlight.model.domain.Tag;
import com.appsterlight.service.ApartmentTagsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class ApartmentTagsServiceImpl implements ApartmentTagsService {
    private final ApartmentTagsDao apartmentTagsDao;


    @Override
    public List<Tag> getAllTagsByApartmentId(Long id) throws ServiceException {
        try {
            return apartmentTagsDao.getAllTagsByApartmentId(id);
        } catch (Exception e) {
            log.error("Exception while retrieving tags of Apartment." + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tag> getAllTagsByApartmentId(Long id, Boolean isBasic) throws ServiceException {
        try {
            return apartmentTagsDao.getAllTagsByApartmentId(id, isBasic);
        } catch (Exception e) {
            log.error("Exception while retrieving tags of Apartment." + e.getMessage());
            throw new ServiceException(e);
        }
    }
}
