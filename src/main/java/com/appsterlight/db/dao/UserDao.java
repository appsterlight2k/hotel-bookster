package com.appsterlight.db.dao;

import com.appsterlight.domain.User;
import com.appsterlight.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> getUserByEmail(String email, Connection con) throws DaoException;
}
