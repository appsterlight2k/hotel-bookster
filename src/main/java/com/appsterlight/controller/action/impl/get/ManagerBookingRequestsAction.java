package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.BookingDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.model.domain.UI.Pagination;
import com.appsterlight.model.domain.UI.Searcher;
import com.appsterlight.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import static com.appsterlight.controller.action.factory.constants.ActionName.ACTION_MANAGER_BOOKING_REQUESTS;
import static com.appsterlight.controller.action.factory.constants.ActionName.ACTION_MANAGER_REQUESTS_FOR_BOOKING;

@Slf4j
public class ManagerBookingRequestsAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();
    private final BookingService bookingService = appContext.getBookingService();
    private int oldPageSize;
    private boolean isInitialize = true;
    private final Boolean isBookingRequest;

    public ManagerBookingRequestsAction(Boolean isBookingRequest) {
        this.isBookingRequest = isBookingRequest;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        LocalDate startDate;
        LocalDate endDate;

        Searcher searcher = new Searcher(req, true);
        String byPeriodCheckedState = searcher.getPeriodCheckboxState(isInitialize);
        searcher.setMinDate("empty");

        startDate = searcher.getStartDay();
        endDate = searcher.getEndDay();

        searcher.showMultiDateControl(true);
        Boolean isMultiDate = searcher.setMultiDateMode(startDate, endDate);

        searcher.showClassControl(false);

        Pagination page = new Pagination(req);
        int pageSize = page.getPageSize();
        page.setPage(oldPageSize);
        Integer offset = page.getOffset();

        Integer count = getBookingsAndCountOfFree(req, byPeriodCheckedState, isMultiDate,
                startDate, endDate, offset, pageSize);

        page.setPagesCount(count);
        page.setPagesAttributes();
        oldPageSize = pageSize;

        req.setAttribute("baseAction", getBaseAction());

        searcher.setSearchResultText(getSearchResultText());
        searcher.setHeaderInfo(getHeaderInfo());
        isInitialize = false;

        return PagesNames.PAGE_MANAGER_REQUESTS;
    }

    private Integer getBookingsAndCountOfFree(HttpServletRequest req, String byPeriodCheckedState, boolean isMultiDate,
                                              LocalDate startDate, LocalDate endDate, Integer offset, Integer pageSize) {
        Integer count = 0;
        List<Booking> bookings;
        try {
            if (byPeriodCheckedState.equals("checked")) {
                if (isMultiDate) {
                    bookings = getAllBookings(startDate, endDate, offset, pageSize);
                    count = getCountOfAllBookings(startDate, endDate);
                } else {
                    bookings = getAllBookings(startDate, offset, pageSize);
                    count = getCountOfAllBookings(startDate);
                }
            } else {
                bookings = getAllBookings(offset, pageSize);
                count = getCountOfAllBookings();
            }
            List<BookingDto> resultList = DtoUtils.mapBookingListToDtoList(bookings);

            req.setAttribute("bookings", resultList);
            req.setAttribute("totalCount", count);
            req.setAttribute("isBookingRequestsTable", isBookingRequest);
        } catch (ServiceException e) {
            log.error("Cant's get all Booking requests! " + e.getMessage());
            throw new RuntimeException(e);
        }

        return count;
    }
    private List<Booking> getAllBookings(LocalDate startDate, Integer offset, Integer pageSize) throws ServiceException {
        return isBookingRequest
                ? bookingService.getAllBookingRequests(startDate, offset, pageSize)
                : bookingService.getAllRequestsForBooking(startDate, offset, pageSize);
    }

    private List<Booking> getAllBookings(LocalDate startDate, LocalDate endDate, Integer offset, Integer pageSize) throws ServiceException {
        return isBookingRequest
                ? bookingService.getAllBookingRequests(startDate, endDate, offset, pageSize)
                : bookingService.getAllRequestsForBooking(startDate, endDate, offset, pageSize);
    }

    private List<Booking> getAllBookings(Integer offset, Integer pageSize) throws ServiceException {
        return isBookingRequest
                ? bookingService.getAllBookingRequests(offset, pageSize)
                : bookingService.getAllRequestsForBooking(offset, pageSize);
    }

    private Integer getCountOfAllBookings() throws ServiceException {
        return isBookingRequest
                ? bookingService.getCountOfAllBookingRequests()
                : bookingService.getCountOfRequestsForBooking();
    }

    private Integer getCountOfAllBookings(LocalDate startDate) throws ServiceException {
        return isBookingRequest
                ? bookingService.getCountOfAllBookingRequests(startDate)
                : bookingService.getCountOfRequestsForBooking(startDate);
    }

    private Integer getCountOfAllBookings(LocalDate startDate, LocalDate endDate) throws ServiceException {
        return isBookingRequest
                ? bookingService.getCountOfAllBookingRequests(startDate, endDate)
                : bookingService.getCountOfRequestsForBooking(startDate, endDate);
    }

    private String getBaseAction() {
        return isBookingRequest ? ACTION_MANAGER_BOOKING_REQUESTS : ACTION_MANAGER_REQUESTS_FOR_BOOKING;
    }

    private String getSearchResultText() {
        return isBookingRequest ? "Total Booking Requests" : "Total Requests for Booking";
    }

    private String getHeaderInfo() {
        return isBookingRequest ? "Booking Requests" : "Requests for Booking";
    }
}
