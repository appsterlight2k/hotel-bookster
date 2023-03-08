package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDate;

@Slf4j
public class BookingAction extends FrontAction {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {

        UserDto user = (UserDto) req.getSession().getAttribute("loggedUser");
        if (user != null) {
            String apartmentId = req.getParameter("apartmentId");
            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");
            String comments = req.getParameter("comments");

            if (startDate != null && endDate != null) {
                try {
                    AppContext appContext = AppContext.getAppContext();
                    BookingService bookingService = appContext.getBookingService();

                    Long id = apartmentId != null ? Long.parseLong(apartmentId) : null;
                    LocalDate checkIn = LocalDate.parse(startDate);
                    LocalDate checkOut = LocalDate.parse(endDate);

                    /*String guestsCount = req.getParameter("guests");*/
                    String guestsCount = req.getParameter("range");
                    Integer guests = Integer.parseInt(guestsCount);

                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                    String result;
                    Booking booking = buildBooking(user, id, checkIn, checkOut, guests, timestamp);
                    if (id != null) {
                        if (!bookingService.isBookingExists(booking)) {
                            bookingService.addBooking(booking);
                            result = "You have booked this Apartment successfully.\n After Manage approved your Application you should confirm the reservation in Bookings!";
                        } else {
                            result = "You have already book this Apartment. Please check your booking list";
                        }
                    } else {
                        bookingService.addBooking(booking);
                        result = "You have send an request Application successfully.\n After Manage approved your Application you should confirm the reservation in Bookings!";
                    }
                    req.setAttribute("result", result);
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
