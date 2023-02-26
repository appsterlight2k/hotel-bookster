package com.appsterlight.model.db.dao;

import com.appsterlight.model.domain.ApartmentPhotos;
import com.appsterlight.exception.DaoException;
import com.appsterlight.shared.Dao;

import java.sql.Connection;
import java.util.List;

public interface ApartmentPhotosDao extends Dao<ApartmentPhotos> {
    List<String> getAllUrlOfPhotosById(Long id) throws DaoException;
}
