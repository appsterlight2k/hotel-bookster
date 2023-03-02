package com.appsterlight.service;

import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Long addUser(User user) throws ServiceException;
    Optional<User> getUserById(Long id) throws ServiceException;
    boolean updateUser(User user) throws ServiceException;
    boolean deleteUser(Long id) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    Optional<User> getUserByEmail(String email);
    boolean isUserExists(String email);

}
