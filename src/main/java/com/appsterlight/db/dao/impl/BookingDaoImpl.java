package com.appsterlight.db.dao.impl;

import com.appsterlight.db.dao.AbstractDao;
import com.appsterlight.db.entity.Booking;
import com.appsterlight.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

import static com.appsterlight.Messages.READ_ERROR;
import static com.appsterlight.db.Fields.*;
import static com.appsterlight.db.Queries.*;

@Slf4j
public class BookingDaoImpl extends AbstractDao<Booking> {
    public BookingDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return null;
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
    protected void setPreparedStatement(PreparedStatement statement, Booking object, boolean isUpdate) throws DaoException {
        int ind = 1;
        try {
            statement.setLong(ind++, object.getUserId());
            statement.setLong(ind++, object.getApartmentId());
            statement.setDate(ind++, Date.valueOf(object.getCheckIn()));
            statement.setDate(ind++, Date.valueOf(object.getCheckOut()));
            statement.setInt(ind++, object.getAdultsNumber());
            statement.setInt(ind++, object.getChildrenNumber());
            statement.setTimestamp(ind++, object.getReservationTime());
            statement.setBoolean(ind++, object.getIsApproved());
            statement.setBoolean(ind++, object.getIsBooked());
            statement.setBoolean(ind++, object.getIsPaid());
            statement.setBoolean(ind++, object.getIsCanceled());
            if (isUpdate) statement.setLong(ind++, object.getId());
        } catch (SQLException e) {
            log.error("Can't set data into Statement!", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    protected Booking mapEntity(ResultSet rs) throws DaoException {
        try {
            return  Booking.builder()
                    .id(rs.getLong(ID))
                    .userId(rs.getLong(BOOKING_USER_ID))
                    .apartmentId(rs.getLong(BOOKING_APARTMENT_ID))
                    .checkIn(rs.getDate(BOOKING_CHECK_IN).toLocalDate())
                    .checkOut(rs.getDate(BOOKING_CHECK_OUT).toLocalDate())
                    .adultsNumber(rs.getInt(BOOKING_ADULTS_NUMBER))
                    .childrenNumber(rs.getInt(BOOKING_CHILDREN_NUMBER))
                    .reservationTime(rs.getTimestamp(BOOKING_RESERVATION_TIME))
                    .isApproved(rs.getBoolean(BOOKING_IS_APPROVED))
                    .isBooked(rs.getBoolean(BOOKING_IS_BOOKED))
                    .isPaid(rs.getBoolean(BOOKING_IS_PAID))
                    .isCanceled(rs.getBoolean(BOOKING_IS_CANCELED))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }

}
