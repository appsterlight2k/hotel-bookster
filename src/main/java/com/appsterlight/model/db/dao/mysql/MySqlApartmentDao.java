package com.appsterlight.model.db.dao.mysql;


import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.ApartmentDao;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.appsterlight.model.db.constants.Messages.*;
import static com.appsterlight.model.db.constants.Queries.*;
import static com.appsterlight.model.db.constants.Fields.*;

@Slf4j
public class MySqlApartmentDao extends AbstractDao<Apartment> implements ApartmentDao {
    private final Connection connection;
    public MySqlApartmentDao(Connection connection) {
        super(connection);
        this.connection = connection;
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
    public Long add(Apartment apartment) throws DaoException {
        Long id = -1L;

        Optional<Apartment> apartmentForCheck = getApartmentByApartmentNumber(apartment.getApartmentNumber());
        if (apartmentForCheck.isEmpty()) {
            id = super.add(apartment);
            apartment.setId(id);
        }

        return id;
    }

    @Override
    public Optional<Apartment> get(Long id) throws DaoException {
        Apartment object = null;

        String query = getSelectQuery();
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                object = mapEntity(rs);
                object.setClassName(rs.getString(APARTMENT_CLASS_NAME));
                object.setClassDescription(rs.getString(APARTMENT_CLASS_DESCRIPTION));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(object);
    }

    @Override
    public List<Apartment> getAll() throws DaoException {
        List<Apartment> objects = new ArrayList<>();

        String query = getSelectAllQuery();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Apartment apartment;
            while (rs.next()) {
                apartment = mapEntity(rs);
                apartment.setClassName(rs.getString(APARTMENT_CLASS_NAME));
                apartment.setClassDescription(rs.getString(APARTMENT_CLASS_DESCRIPTION));
                objects.add(apartment);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return objects;
    }

    @Override
    public Optional<Apartment> getApartmentByApartmentNumber(String number) throws DaoException {
        Apartment object = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_GET_BY_APARTMENT_NUMBER)) {
            prst.setString(1, number);
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
    protected Object[] getAllFieldsOfObject(Apartment object) throws DaoException {
        if (object == null) throw new DaoException("Booking object is null! Can't get fields!");

        return new Object[]{
                object.getApartmentNumber(),
                object.getRoomsCount(),
                object.getClassId(),
                object.getAdultsCapacity(),
                object.getChildrenCapacity(),
                object.getMainPhotoUrl(),
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
                    .mainPhotoUrl(rs.getString(APARTMENT_MAIN_PHOTO_URL))
                    .description(rs.getString(DESCRIPTION))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }

}
