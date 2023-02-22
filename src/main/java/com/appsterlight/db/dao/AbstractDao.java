package com.appsterlight.db.dao;

import com.appsterlight.db.entity.User;
import com.appsterlight.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.appsterlight.Messages.*;
import static com.appsterlight.db.Queries.*;

@Slf4j
public abstract class AbstractDao<T> implements Dao<T>  {
    public abstract String getSelectQuery();
    public abstract String getCreateQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();
    public abstract String getSelectAllQuery();

    protected abstract void setPreparedStatement(PreparedStatement statement, T object, boolean isUpdate) throws DaoException;
    protected abstract T mapEntity(ResultSet rs) throws DaoException;

    private Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long add(T object) throws DaoException {
        Long result = -1L;

        String query = getCreateQuery();
        try (PreparedStatement prst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(prst, object, false);
            if (prst.executeUpdate() > 0) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            log.error(INSERT_ERROR, e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public Optional<T> get(Long id) throws DaoException {
        T object = null;

        String query = getSelectQuery();
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                object = mapEntity(rs);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(object);
    }

    @Override
    public boolean update(T object) throws DaoException {
        boolean result;

        String query = getUpdateQuery();
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            setPreparedStatement(prst, object, true);
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

        String query = getDeleteQuery();
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setLong(1, id);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(DELETE_ERROR, e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> objects = new ArrayList<>();

        String query = getSelectAllQuery();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                objects.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return objects;
    }



}
