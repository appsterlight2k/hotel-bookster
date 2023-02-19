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
import static com.appsterlight.controller.Messages.*;

@Slf4j
public class UserDaoImpl implements UserDao {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(User user) throws DaoException {

        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setPrStParametersForUser(prst, user);

            if (prst.executeUpdate() > 0) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error(INSERT_ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> get(Long id) throws DaoException {
        User user = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_GET)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                user = mapUser(rs);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void update(User user) throws DaoException {

        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_UPDATE)) {
            setPrStParametersForUser(prst, user);
            prst.setLong(8, user.getId());
            prst.executeUpdate();
        } catch (SQLException e) {
            log.error(UPDATE_ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        try (PreparedStatement prst = connection.prepareStatement(SQL_USER_DELETE)) {
            prst.setLong(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            log.error(DELETE_ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_USER_GET_ALL);
            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return users;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        return  User.builder()
                .id(rs.getLong(ID))
                .firstName(rs.getString(USER_FIRST_NAME))
                .lastName(rs.getString(USER_LAST_NAME))
                .email(rs.getString(USER_EMAIL))
                .phoneNumber(rs.getString(USER_PHONE_NUMBER))
                .password(rs.getString(USER_PASSWORD))
                .role(rs.getString(USER_ROLE))
                .description(rs.getString(USER_DESCRIPTION))
                .build();
    }

    private void setPrStParametersForUser(PreparedStatement prst, User user) throws SQLException {
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
