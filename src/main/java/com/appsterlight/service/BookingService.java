package com.appsterlight.service;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Long addBooking(Booking booking) throws ServiceException;
    Optional<Booking> getBookingById(Long id) throws ServiceException;
    boolean updateBooking(Booking booking) throws ServiceException;
    boolean deleteBooking(Long id) throws ServiceException;
    List<Booking> getAllBookings() throws ServiceException;
    boolean isBookingWithParametersExists(Long userId, Long apartmentId, LocalDate checkIn, LocalDate checkOut,
                                          Integer guests) throws ServiceException;
    boolean isBookingExists(Booking booking) throws ServiceException;

    List<Booking> getAllBookingRequests(Integer offset, Integer pageSize) throws ServiceException;
    List<Booking> getAllBookingRequests(LocalDate dateFrom, Integer offset, Integer pageSize) throws ServiceException;
    List<Booking> getAllBookingRequests(LocalDate dateFrom, LocalDate dateTo, Integer offset,
                                        Integer pageSize) throws ServiceException;
    List<Booking> getAllRequestsForBooking(Integer offset, Integer pageSize) throws ServiceException;
    List<Booking> getAllRequestsForBooking(LocalDate dateFrom, Integer offset, Integer pageSize) throws ServiceException;
    List<Booking> getAllRequestsForBooking(LocalDate dateFrom, LocalDate dateTo, Integer offset, Integer pageSize) throws ServiceException;

    Integer getCountOfAllBookingRequests() throws ServiceException;
    Integer getCountOfAllBookingRequests(LocalDate dateFrom) throws ServiceException;
    Integer getCountOfAllBookingRequests(LocalDate dateFrom, LocalDate dateTo) throws ServiceException;
    Integer getCountOfRequestsForBooking() throws ServiceException;
    Integer getCountOfRequestsForBooking(LocalDate dateFrom) throws ServiceException;
    Integer getCountOfRequestsForBooking(LocalDate dateFrom, LocalDate dateTo) throws ServiceException;

    boolean setIsOffered(Long bookingId, Boolean isOffered) throws ServiceException;
    void setIsApprovedState(boolean isApproved, Booking booking) throws ServiceException;
    void setIsBooked(boolean isBooked, Booking booking) throws ServiceException;
    void setIsPaid(boolean isPaid, Booking booking) throws ServiceException;
    void setIsCanceled(boolean isCanceled, Booking booking) throws ServiceException;
}
