package com.appsterlight.service;

import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Booking;


import java.util.List;
import java.util.Optional;

public interface BookingService {
    Long addBooking(Booking booking) throws ServiceException;
    Optional<Booking> getBookingById(Long id) throws ServiceException;
    boolean updateBooking(Booking booking) throws ServiceException;
    boolean deleteBooking(Long id) throws ServiceException;
    List<Booking> getAllBookings() throws ServiceException;
}
