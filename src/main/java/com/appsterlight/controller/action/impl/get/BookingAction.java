package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.ApartmentClass;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.service.ApartmentClassService;
import com.appsterlight.service.ApartmentService;
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
            String isRequestOnly = req.getParameter("isRequestOnly");

            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");
            String comments = req.getParameter("comments");

            if (startDate != null && endDate != null) {
                try {
                    String apartmentIdStr = isRequestOnly.equalsIgnoreCase("true") ? null :
                            req.getParameter("apartmentId");

                    Long apartmentId = (apartmentIdStr != null) ? Long.parseLong(apartmentIdStr) : null;
                    LocalDate checkIn = LocalDate.parse(startDate);
                    LocalDate checkOut = LocalDate.parse(endDate);

                    String guestsCount = req.getParameter("guests");
                    Integer guests = Integer.parseInt(guestsCount);

                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                    String apartmentClass = req.getParameter("apartmentClass");
                    Integer requestClassId = ((apartmentClass == null) ||
                            apartmentClass.equals("0") ||
                            apartmentClass.equalsIgnoreCase("Any Class"))
                            ? null
                            : Integer.parseInt(apartmentClass);

                    Booking booking = buildBooking(user, apartmentId, requestClassId, checkIn, checkOut, guests, timestamp, comments);
                    String message;
                    message = createBooking(booking);
                    req.setAttribute("result", message);
                } catch (Exception e) {
                    log.error("Exception in BookingAction! " + e.getMessage());
                    throw new RuntimeException(e.getMessage());
                }
            }
        }

        return PagesNames.PAGE_CABINET;
    }

    private static String createBooking(Booking booking) throws ServiceException {
        AppContext appContext = AppContext.getAppContext();
        BookingService bookingService = appContext.getBookingService();
        ApartmentClassService apartmentClassService = appContext.getApartmentClassService();
        ApartmentService apartmentService = appContext.getApartmentService();
        String message;

        Integer classId = (booking.getApartmentId() != null)
                ? apartmentService.getApartmentById(booking.getApartmentId()).orElseThrow().getClassId()
                : booking.getRequestClassId();


        ApartmentClass apartmentClass = (classId != null)
                ? apartmentClassService.getApartmentClassById(classId.longValue()).get()
                : null;

        String baseMsg = "start date: " + booking.getCheckIn() + "; end date: " + booking.getCheckOut() + "<br>" +
                "guests: " + booking.getAdultsNumber() + " person(s)" + "<br>" +
                "apartment class: " + ((apartmentClass != null) ?  apartmentClass.getName() : "any") + ". <br>" +
                "After Manager approves your Application you should confirm the reservation in Bookings of Personal cabinet!";

        if (booking.getApartmentId() != null) {

            if (!bookingService.isBookingExists(booking)) {
                bookingService.addBooking(booking);
                message = "You have booked this Apartment successfully.<br> " + baseMsg;

            } else {
                message = "You have already book this Apartment. Please check your booking list in Personal cabinet";
            }
        } else {
            bookingService.addBooking(booking);
            message = "You have send an request Application successfully. <br>" + baseMsg;
        }

        return message;
    }

    private Booking buildBooking(UserDto user, Long apartmentId, Integer requestClassId, LocalDate checkIn, LocalDate checkOut,
                                 Integer guests, Timestamp timeStamp, String comments) {
        return Booking.builder()
                .userId(user.getId())
                .apartmentId(apartmentId)
                .requestClassId(requestClassId)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .adultsNumber(guests)
                .childrenNumber(0)
                .reservationTime(timeStamp)
                .comments(comments)
                .isApproved(false)
                .isBooked(false)
                .isPaid(false)
                .isCanceled(false)
                .build();
    }

}
