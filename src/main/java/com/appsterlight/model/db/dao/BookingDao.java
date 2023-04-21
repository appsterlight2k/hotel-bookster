package com.appsterlight.model.db.dao;

import com.appsterlight.exception.DaoException;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.shared.Dao;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao extends Dao<Booking> {
    List<Booking> getApprovedBookings(Long id, LocalDate checkIn, LocalDate checkOut) throws DaoException;
    List<Booking> getAllBookingRequests(Integer offset, Integer recordsPerPage) throws DaoException;
    List<Booking> getAllBookingRequests(LocalDate dateFrom, Integer offset, Integer recordsPerPage) throws DaoException;
    List<Booking> getAllBookingRequests(LocalDate dateFrom, LocalDate dateTo, Integer offset, Integer recordsPerPage) throws DaoException;

    List<Booking> getAllRequestsForBooking(Integer offset, Integer recordsPerPage) throws DaoException;
    List<Booking> getAllRequestsForBooking(LocalDate dateFrom, Integer offset, Integer recordsPerPage) throws DaoException;
    List<Booking> getAllRequestsForBooking(LocalDate dateFrom, LocalDate dateTo, Integer offset, Integer recordsPerPage) throws DaoException;

    Integer getCountOfBookingRequests() throws DaoException;
    Integer getCountOfBookingRequests(LocalDate dateFrom) throws DaoException;
    Integer getCountOfBookingRequests(LocalDate dateFrom, LocalDate dateTo) throws DaoException;
    Integer getCountOfRequestsForBooking() throws DaoException;
    Integer getCountOfRequestsForBooking(LocalDate dateFrom) throws DaoException;
    Integer getCountOfRequestsForBooking(LocalDate dateFrom, LocalDate dateTo) throws DaoException;

    boolean isBookingExists(Long userId, Long apartmentId, LocalDate checkIn, LocalDate checkOut,
                                   Integer guests) throws DaoException;
    boolean createEventIsPaid(Long id) throws DaoException;
}
