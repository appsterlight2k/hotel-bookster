package com.appsterlight.db.dao;

import com.appsterlight.db.dao.Dao;
import com.appsterlight.db.entity.User;
import com.appsterlight.exceptions.DaoException;

import java.sql.Connection;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> getUserByEmail(String email, Connection con) throws DaoException;
}
