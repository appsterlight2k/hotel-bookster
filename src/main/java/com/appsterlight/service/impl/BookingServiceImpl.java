package com.appsterlight.service.impl;

import com.appsterlight.exception.DaoException;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.db.dao.BookingDao;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;

    @Override
    public Long addBooking(Booking booking) throws ServiceException {
        try {
            return bookingDao.add(booking);
        } catch (DaoException e) {
            log.error("Can't add booking into table", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Booking> getBookingById(Long id) throws ServiceException {
        try {
            return bookingDao.get(id);
        } catch (DaoException e) {
            log.error("Can't get booking with id = " + id + "! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateBooking(Booking booking) throws ServiceException {
        try {
            return bookingDao.update(booking);
        } catch (DaoException e) {
            log.error("Can't update user with id = " + booking.getId() + "! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBooking(Long id) throws ServiceException {
        try {
            return bookingDao.delete(id);
        } catch (DaoException e) {
            log.error("Can't delete booking with id = " + id + "! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Booking> getAllBookings() throws ServiceException {
        try {
            return bookingDao.getAll();
        } catch (DaoException e) {
            log.error("Can't get all bookings from table: " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isBookingWithParametersExists(Long userId, Long apartmentId, LocalDate checkIn, LocalDate checkOut,
                                                 Integer guests) throws ServiceException {
        try {
            return bookingDao.isBookingExists(userId, apartmentId, checkIn, checkOut, guests);
        } catch (DaoException e) {
            log.error("Can't get booking from table! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isBookingExists(Booking booking) throws ServiceException {
        try {
            return bookingDao.isBookingExists(booking.getUserId(), booking.getApartmentId(), booking.getCheckIn(),
                    booking.getCheckOut(), booking.getAdultsNumber());
        } catch (DaoException e) {
            log.error("Can't get booking from table! " + e.getMessage());
            throw new ServiceException(e);
        }
    }


    @Override
    public List<Booking> getAllBookingRequests(Integer offset, Integer pageSize) throws ServiceException {
        try {
            return bookingDao.getAllBookingRequests(offset, pageSize);
        } catch (DaoException e) {
            log.error("Can't get all Booking Requests from table! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Booking> getAllBookingRequests(LocalDate dateFrom, Integer offset, Integer pageSize) throws ServiceException {
        try {
            return bookingDao.getAllBookingRequests(dateFrom, offset, pageSize);
        } catch (DaoException e) {
            log.error("Can't get all Booking Requests from table! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Booking> getAllBookingRequests(LocalDate dateFrom, LocalDate dateTo, Integer offset, Integer pageSize) throws ServiceException {
        try {
            return bookingDao.getAllBookingRequests(dateFrom, dateTo, offset, pageSize);
        } catch (DaoException e) {
            log.error("Can't get all Booking Requests from table! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Booking> getAllRequestsForBooking(Integer offset, Integer pageSize) throws ServiceException {
        try {
            return bookingDao.getAllRequestsForBooking(offset, pageSize);
        } catch (DaoException e) {
            log.error("Can't get all Requests for Booking from table! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Booking> getAllRequestsForBooking(LocalDate dateFrom, Integer offset, Integer pageSize) throws ServiceException {
        try {
            return bookingDao.getAllRequestsForBooking(dateFrom, offset, pageSize);
        } catch (DaoException e) {
            log.error("Can't get all Requests for Booking from table! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Booking> getAllRequestsForBooking(LocalDate dateFrom, LocalDate dateTo, Integer offset, Integer pageSize) throws ServiceException {
        try {
            return bookingDao.getAllRequestsForBooking(dateFrom, dateTo, offset, pageSize);
        } catch (DaoException e) {
            log.error("Can't get all Requests for Booking from table! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfAllBookingRequests(LocalDate dateFrom, LocalDate dateTo) throws ServiceException {
        try {
            return bookingDao.getCountOfBookingRequests(dateFrom, dateTo);
        } catch (DaoException e) {
            log.error("Can't get count of all Bookings Requests! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfAllBookingRequests() throws ServiceException {
        try {
            return bookingDao.getCountOfBookingRequests();
        } catch (DaoException e) {
            log.error("Can't get count of all Bookings Requests! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfAllBookingRequests(LocalDate dateFrom) throws ServiceException {
        try {
            return bookingDao.getCountOfBookingRequests(dateFrom);
        } catch (DaoException e) {
            log.error("Can't get count of all Bookings Requests! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfRequestsForBooking() throws ServiceException {
        try {
            return bookingDao.getCountOfRequestsForBooking();
        } catch (DaoException e) {
            log.error("Can't get count of all Requests for Booking! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfRequestsForBooking(LocalDate dateFrom) throws ServiceException {
        try {
            return bookingDao.getCountOfRequestsForBooking(dateFrom);
        } catch (DaoException e) {
            log.error("Can't get count of all Requests for Booking! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountOfRequestsForBooking(LocalDate dateFrom, LocalDate dateTo) throws ServiceException {
        try {
            return bookingDao.getCountOfRequestsForBooking(dateFrom, dateTo);
        } catch (DaoException e) {
            log.error("Can't get count of all Requests for Booking! " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void setIsApprovedState(boolean isApproved, Booking booking) throws ServiceException {
        try {
            booking.setIsApproved(isApproved);
            updateBooking(booking);
        } catch (ServiceException e) {
            log.error("Exception while updating isApproved state of booking; " + e.getMessage());
            throw new ServiceException(e);
        }
    }
    @Override
    public void setIsBooked(boolean isBooked, Booking booking) throws ServiceException {
        try {
            booking.setIsBooked(isBooked);
            updateBooking(booking);
        } catch (ServiceException e) {
            log.error("Exception while updating isBooked state of booking; " + e.getMessage());
            throw new ServiceException(e);
        }
    }
    @Override
    public void setIsPaid(boolean isPaid, Booking booking) throws ServiceException {
        try {
            booking.setIsPaid(isPaid);
            updateBooking(booking);
        } catch (ServiceException e) {
            log.error("Exception while updating isPaid state of booking; " + e.getMessage());
            throw new ServiceException(e);
        }
    }
    @Override
    public void setIsCanceled(boolean isCanceled, Booking booking) throws ServiceException {
        try {
            booking.setIsCanceled(isCanceled);
            updateBooking(booking);
        } catch (ServiceException e) {
            log.error("Exception while updating isCanceled state of booking; " + e.getMessage());
            throw new ServiceException(e);
        }
    }
}
