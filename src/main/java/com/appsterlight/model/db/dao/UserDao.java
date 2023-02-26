package com.appsterlight.model.db.dao;

import com.appsterlight.model.domain.User;
import com.appsterlight.exception.DaoException;
import com.appsterlight.shared.Dao;

import java.sql.Connection;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> getUserByEmail(String email) throws DaoException;
}
