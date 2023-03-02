package com.appsterlight.model.db.dao;

import com.appsterlight.exception.DaoException;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.shared.Dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingDao extends Dao<Booking> {
    List<Optional<Booking>> getApprovedBookings(Long id, LocalDate checkIn, LocalDate checkOut, Connection con) throws DaoException;

    boolean createEventIsPaid(Long id, Connection con ) throws DaoException;
}
