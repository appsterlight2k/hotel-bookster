package com.appsterlight.db.dao.mysql;

import com.appsterlight.db.dao.AbstractDao;
import com.appsterlight.db.dao.ApartmentDao;
import com.appsterlight.domain.Apartment;
import com.appsterlight.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

import static com.appsterlight.Messages.*;
import static com.appsterlight.db.constants.Fields.*;
import static com.appsterlight.db.constants.Queries.*;

@Slf4j
public class MySqlApartmentDao extends AbstractDao<Apartment> implements ApartmentDao {
    public MySqlApartmentDao(Connection connection) {
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
    public Long add(Apartment object) throws DaoException {
        Long id = super.add(object);
        object.setId(id);

        return id;
    }

    @Override
    protected Object[] getAllFieldsOfObject(Apartment object) throws DaoException {
        if (object == null) throw new DaoException("Booking object is null! Can't get fields!");

        return new Object[]{
                object.getApartmentNumber(),
                object.getRoomsCount(),
                object.getClassId(),
                object.getAdultsCapacity(),
                object.getChildrenCapacity(),
                object.getPrice(),
                object.getDescription(),
                object.getId()
        };
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
