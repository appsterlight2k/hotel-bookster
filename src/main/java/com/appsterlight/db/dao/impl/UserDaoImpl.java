package com.appsterlight.db.dao.impl;

import com.appsterlight.db.dao.UserDao;
import com.appsterlight.db.entity.User;
import com.appsterlight.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.appsterlight.db.Fields.*;
import static com.appsterlight.db.Queries.*;
import static com.appsterlight.Messages.*;

@Slf4j
public class UserDaoImpl implements UserDao {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(User user) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setPrStParametersForEntity(prst, user);
            result = prst.executeUpdate() > 0;
            if (result) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            log.error(INSERT_ERROR, e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<User> get(Long id) throws DaoException {
        User user = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_GET)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                user = mapEntity(rs);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_UPDATE)) {
            setPrStParametersForEntity(prst, user);
            prst.setLong(8, user.getId());
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(UPDATE_ERROR, e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_DELETE)) {
            prst.setLong(1, id);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(DELETE_ERROR, e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_USER_GET_ALL);
            while (rs.next()) {
                users.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return users;
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws DaoException {
        User user = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_GET_BY_EMAIL)) {
            prst.setString(1, email);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                user = mapEntity(rs);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    private User mapEntity(ResultSet rs) throws SQLException {
        return  User.builder()
                .id(rs.getLong(ID))
                .firstName(rs.getString(USER_FIRST_NAME))
                .lastName(rs.getString(USER_LAST_NAME))
                .email(rs.getString(USER_EMAIL))
                .phoneNumber(rs.getString(USER_PHONE_NUMBER))
                .password(rs.getString(USER_PASSWORD))
                .role(rs.getString(USER_ROLE))
                .description(rs.getString(DESCRIPTION))
                .build();
    }

    private void setPrStParametersForEntity(PreparedStatement prst, User user) throws SQLException {
        int ind = 1;
        prst.setString(ind++, user.getFirstName());
        prst.setString(ind++, user.getLastName());
        prst.setString(ind++, user.getEmail());
        prst.setString(ind++, user.getPhoneNumber());
        prst.setString(ind++, user.getPassword());
        prst.setString(ind++, user.getRole());
        prst.setString(ind++, user.getDescription());
    }



}
