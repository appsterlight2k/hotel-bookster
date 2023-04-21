package com.appsterlight.service.impl;

import com.appsterlight.controller.context.AppContext;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.db.dao.ApartmentPhotosDao;
import com.appsterlight.service.ApartmentPhotosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ApartmentPhotosServiceImpl implements ApartmentPhotosService {
    private final ApartmentPhotosDao apartmentPhotosDao;

    @Override
    public List<String> getAllUrlOfPhotosById(Long id) throws ServiceException {
        List<String> list;

        try {
            list = apartmentPhotosDao.getAllUrlOfPhotosById(id);
        } catch (Exception e) {
            log.error(String.format("Exception while retrieving all photo's urls of apartment with id = %s", id));
            throw new ServiceException(e.getMessage());
        }
        return list;
    }
}
