package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.ApartmentDao;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.appsterlight.model.db.constants.Messages.*;
import static com.appsterlight.model.db.constants.Queries.*;
import static com.appsterlight.model.db.constants.Fields.*;
import static com.appsterlight.model.utils.QueryUtils.QueryBuilder;

@Slf4j
public class MySqlApartmentDao extends AbstractDao<Apartment> implements ApartmentDao {
    private final DataSource dataSource;
    public MySqlApartmentDao(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public String getCreateQuery() {
        return SQL_APARTMENT_INSERT;
    }

    @Override
    public String getSelectQuery() {
        return SQL_APARTMENT_GET;
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

        try (Connection connection = dataSource.getConnection();
                PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                object = mapEntity(rs);
                object.setClassName(rs.getString(APARTMENT_CLASS_NAME));
                object.setClassDescription(rs.getString(APARTMENT_CLASS_DESCRIPTION));
                object.setIsUnavailable(rs.getBoolean(APARTMENT_IS_UNAVAILABLE));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(object);
    }

    @Override
    public List<Apartment> getAll() throws DaoException {
        List<Apartment> apartments;

        try {
            apartments = getAllByQuery(getSelectAllQuery());
        } catch (Exception e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return apartments;
    }

    private List<Apartment> getAllByQuery(String query, Object... parameters) throws DaoException {
        List<Apartment> apartments = new ArrayList<>();
        Apartment apartment;
        ResultSet rs;
        try (Connection connection = dataSource.getConnection()) {
            if (parameters.length > 0) {
                PreparedStatement prst = connection.prepareStatement(query);
                for (int i = 0; i < parameters.length; i++) {
                    Object param = parameters[i];
                    setParamsToStatementByType(i + 1, param, prst);
                }
                rs = prst.executeQuery();
            } else {
                Statement st = connection.createStatement();
                rs = st.executeQuery(query);
            }

            while (rs. next()) {
                apartment = mapEntity(rs);
                apartment.setClassName(rs.getString(APARTMENT_CLASS_NAME));
                apartment.setClassDescription(rs.getString(APARTMENT_CLASS_DESCRIPTION));
                apartment.setStatus(rs.getString(APARTMENT_STATUS));
                apartments.add(apartment);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return apartments;
    }

    @Override
    public List<Apartment> getAllApartments(Integer guests, LocalDate checkIn, LocalDate checkOut,
                                                Integer classId, String status, String sortingField,
                                                String sortingOrder, Integer offset, Integer pageSize) throws DaoException {

        String query = QueryBuilder(SQL_APARTMENT_GET_ALL_WITH_STATUS_FULL,
                "ORDER BY subquery.", sortingField, " ", sortingOrder, " LIMIT ?, ?");

        return getAllByQuery(query, checkIn, checkOut, guests, classId, classId, status, offset, pageSize);
    }

    public Integer getCountByQuery(String query, Object... parameters) throws DaoException {
        Integer count = -1;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prst = connection.prepareStatement(query)) {
            if (parameters.length > 0) {
                for (int i = 0; i < parameters.length; i++) {
                    Object param = parameters[i];
                    setParamsToStatementByType(i + 1, param, prst);
                }
                ResultSet rs = prst.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return count;
    }

    @Override
    public Integer getCountOfAllApartments(Integer guests, LocalDate checkIn, LocalDate checkOut, Integer classId,
                                     String status) throws DaoException {

        String query = SQL_APARTMENT_GET_COUNT_OF_ALL_WITH_STATUS_FULL;

        //double classId parameter used for ability to get items with some specific classId or with all classId
        return getCountByQuery(query, checkIn, checkOut, guests, classId, classId, status);
    }

    @Override
    public Optional<Apartment> getApartmentByApartmentNumber(String number) throws DaoException {
        Apartment object = null;

        try (Connection connection = dataSource.getConnection();
                PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_GET_BY_APARTMENT_NUMBER)) {
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


    private void setParamsToStatementByType(Integer i, Object param, PreparedStatement prst) throws DaoException {
        try {
            if (param instanceof Integer) {
                prst.setInt(i, (Integer) param);
            } else if (param instanceof Long) {
                prst.setLong(i, (Long) param);
            } else if (param instanceof String) {
                prst.setString(i, (String) param);
            } else if (param instanceof Boolean) {
                prst.setBoolean(i, (Boolean) param);
            } else if (param instanceof LocalDate) {
                prst.setDate(i, Date.valueOf((LocalDate) param));
            } else if (param == null) {
                prst.setNull(i, java.sql.Types.NULL);
            }
        } catch (Exception e) {
            log.error("Cant set parameter to PreparedStatement by type! " + e.getMessage());
            throw new DaoException(e.getMessage());
        }
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
                object.getIsUnavailable(),
                object.getId()
        };
    }

    @Override
    protected Apartment mapEntity(ResultSet rs) throws DaoException {
        try {
            return Apartment.builder()
                   .id(rs.getLong(ID))
                   .apartmentNumber(rs.getString(APARTMENT_NUMBER))
                   .roomsCount(rs.getInt(APARTMENT_ROOMS_COUNT))
                   .classId(rs.getInt(APARTMENT_CLASS_ID))
                   .adultsCapacity(rs.getInt(APARTMENT_ADULTS_CAPACITY))
                   .childrenCapacity(rs.getInt(APARTMENT_CHILDREN_CAPACITY))
                   .price(rs.getInt(APARTMENT_PRICE))
                   .mainPhotoUrl(rs.getString(APARTMENT_MAIN_PHOTO_URL))
                   .description(rs.getString(DESCRIPTION))
                   .isUnavailable(rs.getBoolean(APARTMENT_IS_UNAVAILABLE))
                   .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }

}
