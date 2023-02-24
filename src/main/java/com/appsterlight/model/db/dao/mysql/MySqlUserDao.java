package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.model.db.constants.Fields;
import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.UserDao;
import com.appsterlight.model.domain.User;
import com.appsterlight.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.appsterlight.model.db.constants.Messages.*;
import static com.appsterlight.model.db.constants.Queries.*;

@Slf4j
public class MySqlUserDao extends AbstractDao<User> implements UserDao {

    public MySqlUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return SQL_USER_GET;
    }

    @Override
    public String getCreateQuery() {
        return SQL_USER_INSERT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_USER_UPDATE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_USER_DELETE;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_USER_GET_ALL;
    }



    @Override
    public Long add(User object) throws DaoException {
        Long id = super.add(object);
        object.setId(id);

        return id;
    }

    @Override
    public Optional<User> getUserByEmail(String email, Connection con) throws DaoException {
        User user = null;

        try (PreparedStatement prst = con.prepareStatement(SQL_USER_GET_BY_EMAIL)) {
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

    @Override
    protected Object[] getAllFieldsOfObject(User user) throws DaoException {
        if (user == null) throw new DaoException("User object is null! Can't get fields!");

        return new Object[]{
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getRole(),
                user.getDescription(),
                user.getId(),
        };
    }

    @Override
    protected User mapEntity(ResultSet rs) throws DaoException {
        try {
            return  User.builder()
                    .id(rs.getLong(Fields.ID))
                    .firstName(rs.getString(Fields.USER_FIRST_NAME))
                    .lastName(rs.getString(Fields.USER_LAST_NAME))
                    .email(rs.getString(Fields.USER_EMAIL))
                    .phoneNumber(rs.getString(Fields.USER_PHONE_NUMBER))
                    .password(rs.getString(Fields.USER_PASSWORD))
                    .role(rs.getString(Fields.USER_ROLE))
                    .description(rs.getString(Fields.DESCRIPTION))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }
}
