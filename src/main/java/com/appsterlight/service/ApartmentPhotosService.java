package com.appsterlight.service;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;

import java.util.List;

public interface ApartmentPhotosService {

    List<String> getAllUrlOfPhotosById(Long id) throws ServiceException;
}
