package com.appsterlight.model.db.dao;

import com.appsterlight.exception.DaoException;
import com.appsterlight.model.domain.ApartmentTag;
import com.appsterlight.model.domain.Tag;
import com.appsterlight.shared.Dao;

import java.util.List;

public interface ApartmentTagsDao extends Dao<ApartmentTag> {
    List<Tag> getAllTagsByApartmentId(Long id) throws DaoException;
    List<ApartmentTag> getAllApartmentTagsByTagId(Long id) throws DaoException;
}
