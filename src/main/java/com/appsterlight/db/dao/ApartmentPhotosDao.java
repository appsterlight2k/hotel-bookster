package com.appsterlight.db.dao;

import com.appsterlight.db.entity.ApartmentPhotos;
import com.appsterlight.exceptions.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ApartmentPhotosDao extends Dao<ApartmentPhotos> {
    List<String> getAllUrlOfPhotosById(Long id, Connection con) throws DaoException;
}
