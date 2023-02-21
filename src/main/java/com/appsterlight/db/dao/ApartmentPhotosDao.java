package com.appsterlight.db.dao;

import com.appsterlight.db.entity.Apartment;
import com.appsterlight.db.entity.ApartmentPhotos;
import com.appsterlight.db.entity.User;
import com.appsterlight.exceptions.DaoException;

import java.util.List;

public interface ApartmentPhotosDao extends Dao<ApartmentPhotos> {
    List<String> getAllUrlOfPhotosById(Long id) throws DaoException;

}
