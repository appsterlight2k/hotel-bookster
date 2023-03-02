package com.appsterlight.model.db.dao;

import com.appsterlight.model.domain.ApartmentPhoto;
import com.appsterlight.exception.DaoException;
import com.appsterlight.shared.Dao;

import java.util.List;

public interface ApartmentPhotosDao extends Dao<ApartmentPhoto> {
    List<String> getAllUrlOfPhotosById(Long id) throws DaoException;
}
