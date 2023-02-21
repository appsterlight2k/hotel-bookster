package com.appsterlight.db.dao.impl;

import com.appsterlight.db.dao.ApartmentDao;
import com.appsterlight.db.entity.Apartment;
import com.appsterlight.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.appsterlight.Messages.*;
import static com.appsterlight.db.Fields.*;
import static com.appsterlight.db.Queries.*;

@Slf4j
public class ApartmentDaoImpl implements ApartmentDao {
    private final Connection connection;

    public ApartmentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(Apartment apartment) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setPrStParametersForEntity(prst, apartment);
            result = prst.executeUpdate() > 0;
            if (result) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    apartment.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            log.error(INSERT_ERROR, e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<Apartment> get(Long id) throws DaoException {
        Apartment apartment = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_GET)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                apartment = mapEntity(rs);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(apartment);
    }

    @Override
    public boolean update(Apartment apartment) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_UPDATE)) {
            setPrStParametersForEntity(prst, apartment);
            prst.setLong(8, apartment.getId());
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

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_DELETE)) {
            prst.setLong(1, id);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(DELETE_ERROR, e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<Apartment> getAll() throws DaoException {
        List<Apartment> apartments = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_APARTMENT_GET_ALL);
            while (rs.next()) {
                apartments.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return apartments;
    }

    private Apartment mapEntity(ResultSet rs) throws SQLException {
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
    }

    private void setPrStParametersForEntity(PreparedStatement prst, Apartment apartment) throws SQLException {
        int ind = 1;
        prst.setString(ind++, apartment.getApartmentNumber());
        prst.setInt(ind++, apartment.getRoomsCount());
        prst.setInt(ind++, apartment.getClassId());
        prst.setInt(ind++, apartment.getAdultsCapacity());
        prst.setInt(ind++, apartment.getChildrenCapacity());
        prst.setInt(ind++, apartment.getPrice());
        prst.setString(ind++, apartment.getDescription());
    }
}
