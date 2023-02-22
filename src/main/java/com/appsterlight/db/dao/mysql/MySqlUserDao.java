package com.appsterlight.db.dao.mysql;

import com.appsterlight.db.dao.AbstractDao;
import com.appsterlight.db.dao.UserDao;
import com.appsterlight.db.entity.User;
import com.appsterlight.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.appsterlight.Messages.*;
import static com.appsterlight.db.Fields.*;
import static com.appsterlight.db.Queries.*;

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
    protected void setPreparedStatement(PreparedStatement statement, User user, boolean isUpdate) throws DaoException {
        int ind = 1;
        try {
            statement.setString(ind++, user.getFirstName());
            statement.setString(ind++, user.getLastName());
            statement.setString(ind++, user.getEmail());
            statement.setString(ind++, user.getPhoneNumber());
            statement.setString(ind++, user.getPassword());
            statement.setString(ind++, user.getRole());
            statement.setString(ind++, user.getDescription());
            if (isUpdate) statement.setLong(ind++, user.getId());
        } catch (SQLException e) {
            log.error(STATEMENT_ERROR, e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    protected User mapEntity(ResultSet rs) throws DaoException {
        try {
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
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }
}
