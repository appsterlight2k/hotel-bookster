package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.model.db.constants.Fields;
import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.BookingDao;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.exception.DaoException;
import com.appsterlight.model.domain.BookingExtended;
import com.appsterlight.model.domain.RequestBookingExtended;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;


import static com.appsterlight.model.db.constants.Messages.*;
import static com.appsterlight.model.db.constants.Queries.*;

@Slf4j
public class MySqlBookingDao extends AbstractDao<Booking> implements BookingDao {
    private final DataSource dataSource;

    public MySqlBookingDao(DataSource dataSource) {

        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public String getSelectQuery() {
        return SQL_BOOKING_GET;
    }

    @Override
    public String getCreateQuery() {
        return SQL_BOOKING_INSERT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_BOOKING_UPDATE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_BOOKING_DELETE;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_BOOKING_GET_ALL;
    }


    @Override
    public Long add(Booking object) throws DaoException {
        Long id = super.add(object);
        object.setId(id);

        return id;
    }

    @Override
    public boolean isBookingExists(Long userId, Long apartmentId, LocalDate checkIn, LocalDate checkOut,
                                   Integer guests) throws DaoException {
        Integer count;

        try (Connection connection = dataSource.getConnection();
                PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_GET_BOOKING_COUNT)) {
            prst.setLong(1, userId);
            prst.setLong(2, apartmentId);
            prst.setDate(3, Date.valueOf(checkIn));
            prst.setDate(4, Date.valueOf(checkOut));
            prst.setInt(5, guests);

            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return false;
    }

    public List<Booking> getApprovedBookings(Long id, LocalDate checkIn, LocalDate checkOut) throws DaoException {
        return getAllByQuery(true, SQL_BOOKING_GET_IS_APPROVED, id, checkIn, checkOut);
    }

    public List<Booking> getAllBookingRequests(Integer offset, Integer recordsPerPage) throws DaoException {
        return getAllByQuery(true, SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_WITH_PAGINATION,
                offset, recordsPerPage);
    }

    public List<Booking> getAllBookingRequests(LocalDate dateFrom, Integer offset, Integer recordsPerPage) throws DaoException {
        return getAllByQuery(true, SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_AFTER_DATE_WITH_PAGINATION,
                dateFrom, offset, recordsPerPage);
    }

    @Override
    public List<Booking> getAllBookingRequests(LocalDate dateFrom, LocalDate dateTo, Integer offset, Integer recordsPerPage) throws DaoException {
        return getAllByQuery(true, SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_BY_PERIOD_AND_PAGINATION,
                dateFrom, dateTo, offset, recordsPerPage);
    }

    public List<Booking> getAllRequestsForBooking(Integer offset, Integer recordsPerPage) throws DaoException {
        return getAllByQuery(false, SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_WITH_PAGINATION,
                offset, recordsPerPage);
    }

    @Override
    public List<Booking> getAllRequestsForBooking(LocalDate dateFrom, Integer offset, Integer recordsPerPage) throws DaoException {
        return getAllByQuery(false, SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_AFTER_DATE_WITH_PAGINATION,
                dateFrom, offset, recordsPerPage);
    }

    @Override
    public List<Booking> getAllRequestsForBooking(LocalDate dateFrom, LocalDate dateTo, Integer offset, Integer recordsPerPage) throws DaoException {
        return getAllByQuery(false, SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_BY_PERIOD_AND_PAGINATION,
                dateFrom, dateTo, offset, recordsPerPage);
    }


    //    SQL_BOOKING_GET_COUNT_OF_ALL_REQUEST_FOR_BOOKING
    @Override
    public Integer getCountOfBookingRequests() throws DaoException {
         return getCountOfRequest(SQL_BOOKING_GET_COUNT_OF_ALL_BOOKING_REQUEST);
    }

    @Override
    public Integer getCountOfBookingRequests(LocalDate dateFrom) throws DaoException {
        return getCountOfRequest(SQL_BOOKING_GET_COUNT_OF_ALL_BOOKING_REQUEST_AFTER_DATE, dateFrom);
    }

    @Override
    public Integer getCountOfBookingRequests(LocalDate dateFrom, LocalDate dateTo) throws DaoException {
        return getCountOfRequest(SQL_BOOKING_GET_COUNT_OF_ALL_BOOKING_REQUEST_BY_PERIOD, dateFrom, dateTo);
    }

    @Override
    public Integer getCountOfRequestsForBooking() throws DaoException {
         return getCountOfRequest(SQL_BOOKING_GET_COUNT_OF_ALL_REQUEST_FOR_BOOKING);
    }

    @Override
    public Integer getCountOfRequestsForBooking(LocalDate dateFrom) throws DaoException {
        return getCountOfRequest(SQL_BOOKING_GET_COUNT_OF_ALL_REQUEST_FOR_BOOKING_AFTER_DATE, dateFrom);
    }

    @Override
    public Integer getCountOfRequestsForBooking(LocalDate dateFrom, LocalDate dateTo) throws DaoException {
        return getCountOfRequest(SQL_BOOKING_GET_COUNT_OF_ALL_REQUEST_FOR_BOOKING_BY_PERIOD, dateFrom, dateTo);
    }

    private Integer getCountOfRequest(String query) throws DaoException {
        Integer count = -1;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prst = connection.prepareStatement(query)) {
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return count;
    }

    private Integer getCountOfRequest(String query, Object... parameters) throws DaoException {
        Integer count = -1;

        try (Connection connection = dataSource.getConnection();
            PreparedStatement prst = connection.prepareStatement(query);
            Statement st = connection.createStatement();) {

            ResultSet rs;
            if (parameters.length > 0) {
                for (int i = 0; i < parameters.length; i++) {
                    Object param = parameters[i];
                    setParamsToStatementByType(i + 1, param, prst);
                }
                rs = prst.executeQuery();
            } else {
                rs = st.executeQuery(query);
            }

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return count;
    }


    private List<Booking> getAllByQuery(boolean isBookingRequest, String query, Object... parameters) throws DaoException {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prst = connection.prepareStatement(query);
             Statement st = connection.createStatement();) {
            ResultSet rs;
            if (parameters.length > 0) {
                for (int i = 0; i < parameters.length; i++) {
                    Object param = parameters[i];
                    setParamsToStatementByType(i + 1, param, prst);
                }
                rs = prst.executeQuery();
            } else {
                rs = st.executeQuery(query);
            }

            Booking booking;
            while (rs. next()) {
                booking =  isBookingRequest ? mapEntity(rs) : mapRequestBookingExtendedEntity(rs);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return bookings;
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
            }
        } catch (Exception e) {
            log.error("Cant set parameter to PreparedStatement by type! " + e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

    /* EVENTS */
    @Override
    public boolean createEventIsPaid(Long id) throws DaoException {
        log.info("Query: " + SQL_BOOKING_CREATE_EVENT_IS_PAID);

        Formatter formatter = new Formatter();
        formatter.format(SQL_BOOKING_CREATE_EVENT_IS_PAID, "isPaidByUser" + id);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(formatter.toString()) ) {
            stmt.setLong(1, id);

            return stmt.execute();

        } catch (SQLException e){
            log.error(EVENT_OF_PAY_ERROR + ": " + e.getMessage());
            throw new DaoException(EVENT_OF_PAY_ERROR, e);
        }
    }

    @Override
    protected Object[] getAllFieldsOfObject(Booking object) throws DaoException {
        if (object == null) throw new DaoException("Booking object is null! Can't get fields!");

        return new Object[]{
                object.getUserId(),
                object.getApartmentId(),
                object.getRequestClassId(),
                Date.valueOf(object.getCheckIn()),
                Date.valueOf(object.getCheckOut()),
                object.getAdultsNumber(),
                object.getChildrenNumber(),
                object.getReservationTime(),
                object.getComments(),
                object.getIsApproved(),
                object.getIsBooked(),
                object.getIsPaid(),
                object.getIsCanceled(),
                object.getId()
        };
    }


    @Override
    protected Booking mapEntity(ResultSet rs) throws DaoException {
        try {
            return BookingExtended.builder()
                .id(rs.getLong(Fields.ID))
                .userId(rs.getLong(Fields.BOOKING_USER_ID))
                .apartmentId(rs.getLong(Fields.BOOKING_APARTMENT_ID))
                .requestClassId(rs.getInt(Fields.BOOKING_REQUEST_CLASS_ID))
                .checkIn(rs.getDate(Fields.BOOKING_CHECK_IN).toLocalDate())
                .checkOut(rs.getDate(Fields.BOOKING_CHECK_OUT).toLocalDate())
                .adultsNumber(rs.getInt(Fields.BOOKING_ADULTS_NUMBER))
                .childrenNumber(rs.getInt(Fields.BOOKING_CHILDREN_NUMBER))
                .reservationTime(rs.getTimestamp(Fields.BOOKING_RESERVATION_TIME))
                .comments(rs.getString(Fields.BOOKING_COMMENTS))
                .isApproved(rs.getBoolean(Fields.BOOKING_IS_APPROVED))
                .isBooked(rs.getBoolean(Fields.BOOKING_IS_BOOKED))
                .isPaid(rs.getBoolean(Fields.BOOKING_IS_PAID))
                .isCanceled(rs.getBoolean(Fields.BOOKING_IS_CANCELED))
                //additional fields:
                .firstName(rs.getString(Fields.USER_FIRST_NAME))
                .lastName(rs.getString(Fields.USER_LAST_NAME))
                .email(rs.getString(Fields.USER_EMAIL))
                .userPhoneNumber(rs.getString(Fields.BOOKING_USER_PHONE_NUMBER))
                .userDescription(rs.getString(Fields.BOOKING_USER_DESCRIPTION))
                .apartmentNumber(rs.getString(Fields.APARTMENT_NUMBER))
                .apartmentClass(rs.getString(Fields.BOOKING_APARTMENT_CLASS))
                .roomsCount(rs.getInt(Fields.APARTMENT_ROOMS_COUNT))
                .capacity(rs.getInt(Fields.APARTMENT_ADULTS_CAPACITY))
                .price(rs.getInt(Fields.APARTMENT_PRICE))
                .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }

    private Booking mapRequestBookingExtendedEntity(ResultSet rs) throws DaoException {
        try {
            return  RequestBookingExtended.builder()
                .id(rs.getLong(Fields.ID))
                .userId(rs.getLong(Fields.BOOKING_USER_ID))
                .apartmentId(rs.getLong(Fields.BOOKING_APARTMENT_ID))
                .requestClassId(rs.getInt(Fields.BOOKING_REQUEST_CLASS_ID))
                .checkIn(rs.getDate(Fields.BOOKING_CHECK_IN).toLocalDate())
                .checkOut(rs.getDate(Fields.BOOKING_CHECK_OUT).toLocalDate())
                .adultsNumber(rs.getInt(Fields.BOOKING_ADULTS_NUMBER))
                .childrenNumber(rs.getInt(Fields.BOOKING_CHILDREN_NUMBER))
                .reservationTime(rs.getTimestamp(Fields.BOOKING_RESERVATION_TIME))
                .comments(rs.getString(Fields.BOOKING_COMMENTS))
                .isApproved(rs.getBoolean(Fields.BOOKING_IS_APPROVED))
                .isBooked(rs.getBoolean(Fields.BOOKING_IS_BOOKED))
                .isPaid(rs.getBoolean(Fields.BOOKING_IS_PAID))
                .isCanceled(rs.getBoolean(Fields.BOOKING_IS_CANCELED))
                //additional fields:
                .firstName(rs.getString(Fields.USER_FIRST_NAME))
                .lastName(rs.getString(Fields.USER_LAST_NAME))
                .email(rs.getString(Fields.USER_EMAIL))
                .userPhoneNumber(rs.getString(Fields.BOOKING_USER_PHONE_NUMBER))
                .userDescription(rs.getString(Fields.BOOKING_USER_DESCRIPTION))
                .apartmentClass(rs.getString(Fields.BOOKING_APARTMENT_CLASS))
                .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }


}
