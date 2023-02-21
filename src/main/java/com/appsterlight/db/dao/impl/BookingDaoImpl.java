package com.appsterlight.db.dao.impl;

import com.appsterlight.db.dao.BookingDao;
import com.appsterlight.db.entity.Booking;
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
public class BookingDaoImpl implements BookingDao {
    private final Connection connection;

    public BookingDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(Booking booking) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setPrStParametersForEntity(prst, booking, false);
            result = prst.executeUpdate() > 0;
            if (result) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    booking.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            log.error(INSERT_ERROR, e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<Booking> get(Long id) throws DaoException {
        Booking booking = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_GET)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                booking = mapEntity(rs);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(booking);
    }

    @Override
    public boolean update(Booking booking) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_UPDATE)) {
            setPrStParametersForEntity(prst, booking, true);

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

        try (PreparedStatement prst = connection.prepareStatement(SQL_BOOKING_DELETE)) {
            prst.setLong(1, id);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(DELETE_ERROR, e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<Booking> getAll() throws DaoException {
        List<Booking> bookings = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_BOOKING_GET_ALL);
            while (rs.next()) {
                bookings.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return bookings;
    }

    private Booking mapEntity(ResultSet rs) throws SQLException {
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
    }

    private void setPrStParametersForEntity(PreparedStatement prst, Booking booking, boolean is_update) throws SQLException {
        int ind = 1;
        prst.setLong(ind++, booking.getUserId());
        prst.setLong(ind++, booking.getApartmentId());
        prst.setDate(ind++, Date.valueOf(booking.getCheckIn()));
        prst.setDate(ind++, Date.valueOf(booking.getCheckOut()));
        prst.setInt(ind++, booking.getAdultsNumber());
        prst.setInt(ind++, booking.getChildrenNumber());
        prst.setTimestamp(ind++, booking.getReservationTime());
        prst.setBoolean(ind++, booking.getIsApproved());
        prst.setBoolean(ind++, booking.getIsBooked());
        prst.setBoolean(ind++, booking.getIsPaid());
        prst.setBoolean(ind++, booking.getIsCanceled());
        if (is_update) prst.setLong(ind++, booking.getId());
    }
}
