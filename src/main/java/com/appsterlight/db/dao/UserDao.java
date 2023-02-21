package com.appsterlight.db.dao;

import com.appsterlight.db.entity.Booking;
import com.appsterlight.db.entity.User;
import com.appsterlight.exceptions.DaoException;

import java.util.Optional;


public interface UserDao extends Dao<User> {
    Optional<User> getUserByEmail(String email) throws DaoException;

}
