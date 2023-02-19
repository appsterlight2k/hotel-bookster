package com.appsterlight.db.dao;

import com.appsterlight.exceptions.DaoException;

import java.util.List;
import java.util.Optional;


public interface Dao<T> {

    void add(T t) throws DaoException;

    Optional<T> get(Long id) throws DaoException;

    void update(T t) throws DaoException;

    void delete(Long id) throws DaoException;

    List<T> getAll() throws DaoException;


}
