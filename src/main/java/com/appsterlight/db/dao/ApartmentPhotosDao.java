package com.appsterlight.db.dao;

import com.appsterlight.domain.ApartmentPhotos;
import com.appsterlight.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ApartmentPhotosDao extends Dao<ApartmentPhotos> {
    List<String> getAllUrlOfPhotosById(Long id, Connection con) throws DaoException;
}
