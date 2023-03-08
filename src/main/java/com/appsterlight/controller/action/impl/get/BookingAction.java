package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
public class BookingAction extends FrontAction {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {


        UserDto user = (UserDto) req.getSession().getAttribute("loggedUser");
        if (user != null) {
            String apartmentId = req.getParameter("apartmentId");
            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");

            if (apartmentId != null && startDate != null && endDate != null) {
                try {
                    AppContext appContext = AppContext.getAppContext();
                    BookingService bookingService = appContext.getBookingService();

                    Long id = Long.parseLong(apartmentId);

                    LocalDate checkIn = LocalDate.parse(startDate);
                    LocalDate checkOut = LocalDate.parse(endDate);

                    String guestsCount = req.getParameter("guests");
                    Integer guests = Integer.parseInt(guestsCount);

                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                    Booking booking = buildBooking(user, id, checkIn, checkOut, guests, timestamp);

                    bookingService.addBooking(booking);
                } catch (Exception e) {
                    log.error("Exception in BookingAction! " + e.getMessage());
                    throw new RuntimeException(e.getMessage());
                }
            }
        }

        return PagesNames.PAGE_CABINET;
    }

    private Booking buildBooking(UserDto user, Long apartmentId, LocalDate checkIn, LocalDate checkOut,
                                 Integer guests, Timestamp timeStamp) {
        return Booking.builder()
                .userId(user.getId())
                .apartmentId(apartmentId)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .adultsNumber(guests)
                .childrenNumber(0)
                .reservationTime(timeStamp)
                .isApproved(false)
                .isBooked(false)
                .isPaid(false)
                .isCanceled(false)
                .build();
    }

}
