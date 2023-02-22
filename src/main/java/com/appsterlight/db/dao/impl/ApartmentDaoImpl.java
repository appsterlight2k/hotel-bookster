package com.appsterlight.db.dao.impl;

import com.appsterlight.db.dao.AbstractDao;
import com.appsterlight.db.entity.Apartment;
import com.appsterlight.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.appsterlight.Messages.READ_ERROR;
import static com.appsterlight.db.Fields.*;
import static com.appsterlight.db.Queries.*;

@Slf4j
public class ApartmentDaoImpl extends AbstractDao<Apartment> {
    public ApartmentDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return SQL_APARTMENT_GET;
    }

    @Override
    public String getCreateQuery() {
        return SQL_APARTMENT_INSERT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_APARTMENT_UPDATE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_APARTMENT_DELETE;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_APARTMENT_GET_ALL;
    }

    @Override
    protected void setPreparedStatement(PreparedStatement statement, Apartment object, boolean isUpdate) throws DaoException {
        int ind = 1;
        try {
            statement.setString(ind++, object.getApartmentNumber());
            statement.setInt(ind++, object.getRoomsCount());
            statement.setInt(ind++, object.getClassId());
            statement.setInt(ind++, object.getAdultsCapacity());
            statement.setInt(ind++, object.getChildrenCapacity());
            statement.setInt(ind++, object.getPrice());
            statement.setString(ind++, object.getDescription());
            if (isUpdate) statement.setLong(ind++, object.getId());
        } catch (SQLException e) {
            log.error("Can't set data into Statement!", e.getMessage());
            throw new DaoException(e);
        }

    }

    @Override
    protected Apartment mapEntity(ResultSet rs) throws DaoException {
        try {
            return  Apartment.builder()
                    .id(rs.getLong(ID))
                    .apartmentNumber(rs.getString(APARTMENT_NUMBER))
                    .roomsCount(rs.getInt(APARTMENT_ROOMS_COUNT))
                    .classId(rs.getInt(APARTMENT_CLASS_ID))
                    .adultsCapacity(rs.getInt(APARTMENT_ADULTS_CAPACITY))
                    .childrenCapacity(rs.getInt(APARTMENT_CHILDREN_CAPACITY))
                    .price(rs.getInt(APARTMENT_PRICE))
                    .description(rs.getString(DESCRIPTION))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }



}
