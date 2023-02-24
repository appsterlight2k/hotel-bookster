package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.model.db.constants.Fields;
import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.BookingDao;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

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
