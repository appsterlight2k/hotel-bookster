package com.appsterlight.service.impl;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.db.dao.UserDao;
import com.appsterlight.model.domain.User;
import com.appsterlight.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public Long addUser(User user) throws ServiceException {
        try {
            return userDao.add(user);
        } catch (DaoException e) {
            log.error(String.format("Can't add user into table because of %s", e.getMessage()));
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> getUserById(Long id) throws ServiceException {
        try {
            return userDao.get(id);
        } catch (DaoException e) {
            log.error(String.format("Can't get user with id = %s", id), e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            log.error(String.format("Can't update user with id = %s", user.getId()), e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteUser(Long id) throws ServiceException {
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            log.error(String.format("Can't delete user with id = %s", id), e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            log.error(String.format("Can't get all users from table: %s", e.getMessage()));
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        try {
            return userDao.getUserByEmail(email);
        } catch (DaoException e) {
            log.error(String.format("Can't get user with email %s", e.getMessage()));
            return Optional.empty();
        }
    }

    @Override
    public boolean isUserExists(String email) {
        return getUserByEmail(email).isPresent();
    }


}
