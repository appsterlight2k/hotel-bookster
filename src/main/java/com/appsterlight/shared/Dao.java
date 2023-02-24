package com.appsterlight.shared;

import com.appsterlight.exception.DaoException;

import java.util.List;
import java.util.Optional;


public interface Dao<T> {

    Long add(T t) throws DaoException;

    Optional<T> get(Long id) throws DaoException;

    boolean update(T t) throws DaoException;

    boolean delete(Long id) throws DaoException;

    List<T> getAll() throws DaoException;


}
