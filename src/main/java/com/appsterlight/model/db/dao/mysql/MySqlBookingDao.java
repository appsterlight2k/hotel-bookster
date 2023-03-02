package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.model.db.constants.Fields;
import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.BookingDao;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

import static com.appsterlight.model.db.constants.Messages.*;
import static com.appsterlight.model.db.constants.Queries.*;

@Slf4j
public class MySqlBookingDao extends AbstractDao<Booking> implements BookingDao {
    public MySqlBookingDao(Connection connection) {
        super(connection);
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
    public List<Optional<Booking>> getApprovedBookings(Long id, LocalDate checkIn, LocalDate checkOut, Connection connection) throws DaoException {
        List<Optional<Booking>> list = new ArrayList<>();

        try (PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_GET_IS_APPROVED)) {
            prst.setLong(1, id);
            prst.setDate(2, Date.valueOf(checkIn));
            prst.setDate(2, Date.valueOf(checkOut));

            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                list.add(Optional.of(mapEntity(rs)));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return list;
    }

    public Optional<Booking> getBookedBookings(Long id, LocalDate checkIn, LocalDate checkOut, Connection connection) throws DaoException {
        Booking object = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_GET_IS_BOOKED)) {
            prst.setLong(1, id);
            prst.setDate(2, Date.valueOf(checkIn));
            prst.setDate(2, Date.valueOf(checkOut));

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

    public Optional<Booking> getPaidBookings(Long id, LocalDate checkIn, LocalDate checkOut, Connection connection) throws DaoException {
        Booking object = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_GET_IS_PAID)) {
            prst.setLong(1, id);
            prst.setDate(2, Date.valueOf(checkIn));
            prst.setDate(2, Date.valueOf(checkOut));

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

    public Optional<Booking> getCanceledBookings(Long id, LocalDate checkIn, LocalDate checkOut, Connection connection) throws DaoException {
        Booking object = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_GET_IS_CANCELED)) {
            prst.setLong(1, id);
            prst.setDate(2, Date.valueOf(checkIn));
            prst.setDate(2, Date.valueOf(checkOut));

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


    /* EVENTS */
    @Override
    public boolean createEventIsPaid(Long id, Connection connection) throws DaoException {
        log.info("Query: " + SQL_BOOKING_CREATE_EVENT_IS_PAID);

        Formatter formatter = new Formatter();
        formatter.format(SQL_BOOKING_CREATE_EVENT_IS_PAID, "isPaidByUser" + id);

        try (PreparedStatement stmt = connection.prepareStatement(formatter.toString()) ) {
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
                Date.valueOf(object.getCheckIn()),
                Date.valueOf(object.getCheckOut()),
                object.getAdultsNumber(),
                object.getChildrenNumber(),
                object.getReservationTime(),
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
            return  Booking.builder()
                    .id(rs.getLong(Fields.ID))
                    .userId(rs.getLong(Fields.BOOKING_USER_ID))
                    .apartmentId(rs.getLong(Fields.BOOKING_APARTMENT_ID))
                    .checkIn(rs.getDate(Fields.BOOKING_CHECK_IN).toLocalDate())
                    .checkOut(rs.getDate(Fields.BOOKING_CHECK_OUT).toLocalDate())
                    .adultsNumber(rs.getInt(Fields.BOOKING_ADULTS_NUMBER))
                    .childrenNumber(rs.getInt(Fields.BOOKING_CHILDREN_NUMBER))
                    .reservationTime(rs.getTimestamp(Fields.BOOKING_RESERVATION_TIME))
                    .isApproved(rs.getBoolean(Fields.BOOKING_IS_APPROVED))
                    .isBooked(rs.getBoolean(Fields.BOOKING_IS_BOOKED))
                    .isPaid(rs.getBoolean(Fields.BOOKING_IS_PAID))
                    .isCanceled(rs.getBoolean(Fields.BOOKING_IS_CANCELED))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }


}
