package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.action.utils.SessionUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentClassDto;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.*;
import com.appsterlight.model.domain.UI.Pagination;
import com.appsterlight.model.domain.UI.Searcher;
import com.appsterlight.model.domain.UI.UIController;
import com.appsterlight.service.ApartmentService;
import com.appsterlight.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static com.appsterlight.controller.action.factory.constants.ActionName.*;
import static com.appsterlight.controller.action.utils.ControllerUtils.isGetMethod;
import static com.appsterlight.controller.constants.PagesNames.JSON_RESPONSE;

@Slf4j
public class ApartmentsAction extends FrontAction {
    private final AppContext appContext = AppContext.getAppContext();
    private int oldPageSize;
    private int oldModalPageSize;
    private boolean isInitialize = true;
    List<ApartmentClassDto> allApartmentClasses;

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        return isGetMethod(req) ? doGet(req, resp) : doPost(req, resp);
    }

    private String doGet(HttpServletRequest req, HttpServletResponse res) {
        Role role = SessionUtils.getActiveRole(req);

        UIController ui = new UIController(req);
        ui.init();

        Searcher searcher = new Searcher(req, false);
        searcher.setMinDate(); //today
        LocalDate startDate = searcher.getStartDay();
        LocalDate endDate = searcher.getEndDay();
        searcher.setMultiDateMode(startDate, endDate);

        searcher.showGuestsControl(true);
        Integer guests = searcher.getGuests();
        String status = searcher.getApartmentStatus();
        searcher.showClassControl(true);

        if (allApartmentClasses == null) {
            allApartmentClasses = searcher.getApartmentClasses();
        }
        searcher.setApartmentClasses(allApartmentClasses);

        Pagination page = new Pagination(req);
        Integer pageSize = page.getPageSize();
        page.setPage(oldPageSize);
        Integer offset = page.getOffset();

        String sortingField = "price";
        String sortingOrder = "ASC";

        String apartmentClass = searcher.getApartmentClass();

        Integer totalCount = getAllApartments(req, role, apartmentClass, guests, startDate, endDate, status,
                sortingField, sortingOrder, offset, pageSize);

        //set pagination parameters:
        page.setPagesCount(totalCount);
        page.setPagesAttributes();

        oldPageSize = pageSize;

        req.setAttribute("baseAction", ACTION_APARTMENTS);
        if (role == Role.ROLE_MANAGER) {
            req.setAttribute("showStatuses", true);
            searcher.showDescriptionControl(true);
        } else {
            if (role == Role.ROLE_USER)
                req.setAttribute("secondaryAction", ACTION_BOOKING);
            searcher.getPeriodCheckboxState(isInitialize);
        }

        searcher.setSearchResultText("Apartments found");
        searcher.setHeaderInfo("Apartments");
        isInitialize = false;

        return PagesNames.PAGE_APARTMENTS;
    }

    //used for AJAX-calls
    private String doPost(HttpServletRequest req, HttpServletResponse res) {
        BookingService bookingService = appContext.getBookingService();
        ApartmentService apartmentService = appContext.getApartmentService();

        List<Apartment> apartments;
        List<ApartmentDto> result = new ArrayList<>();
        Integer count = 0;
        Integer offset = 0;
        Integer pageSize = 0;
        Integer page = 0;

        try {
            BufferedReader reader= req.getReader();
            Gson gson = new Gson();
            DataObject data = gson.fromJson(reader, DataObject.class);
            Integer id = data.getBookingId();
            page = data.getPage();
            pageSize = data.getPageSize();

            Optional<Booking> booking = bookingService.getBookingById(id.longValue());

            if (booking.isPresent()) {
                Integer classId = booking.get().getRequestClassId();
                classId = classId == 0 ? null : classId;

                Integer guests = booking.get().getAdultsNumber();
                LocalDate checkIn = booking.get().getCheckIn();
                LocalDate checkOut = booking.get().getCheckOut();

                String sortingField = "price";
                String sortingOrder = "ASC";

                offset = (page - 1) * pageSize;
                apartments = apartmentService.getAllApartments(guests, checkIn, checkOut, classId,
                        "free", sortingField, sortingOrder, offset, pageSize);
                count = apartmentService.getCountOfAllApartments(guests, checkIn, checkOut, classId, "free");

                result = DtoUtils.mapApartmentListToDtoList(apartments);
            }

            Integer pagesCount = (int) Math.ceil((double) count / pageSize);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("apartments", result);
            responseMap.put("totalCount", count);
            responseMap.put("offset", offset);
            responseMap.put("modalPageSize", pageSize);
            responseMap.put("pagesCount", pagesCount);
            responseMap.put("page", page);


            //Send Map as JSON response
            ObjectMapper mapper = new ObjectMapper();
            res.setContentType("application/json");
            PrintWriter out = res.getWriter();
            out.print(mapper.writeValueAsString(responseMap));
            out.flush();
        } catch (JsonProcessingException e) {
            log.error("Error! Can't parse JSON of Apartments!", e);
        } catch (ServiceException e) {
            log.error("Error! Can't get Booking by id! ", e);
        } catch (IOException e) {
            log.error("Error! Can't write JSON of Apartments during Response! ", e);
        }

        return JSON_RESPONSE;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class DataObject {
        private Integer bookingId;
        private Integer page;
        private Integer pageSize;
    }

    private Integer getAllApartments(HttpServletRequest req, Role role, String apartmentClass, Integer guests,
                                     LocalDate startDate, LocalDate endDate, String status, String sortingField,
                                     String sortingOrder, Integer offset, Integer pageSize) {

        ApartmentService apartmentService = appContext.getApartmentService();

        Integer total = -1;
        List<Apartment> apartments;
        List<ApartmentDto> result;

        Integer classId = (apartmentClass == null)
                || apartmentClass.equals("0")
                || apartmentClass.equalsIgnoreCase("Any Class")
                ? null
                : Integer.parseInt(apartmentClass);

        try {
            status = (role == Role.ROLE_MANAGER) ? status : "free";

            apartments = apartmentService.getAllApartments(guests, startDate, endDate, classId, status, sortingField,
                    sortingOrder, offset, pageSize);
            total = apartmentService.getCountOfAllApartments(guests, startDate, endDate, classId, status);

            result = DtoUtils.mapApartmentListToDtoList(apartments);

            req.setAttribute("apartments", result);
            req.setAttribute("totalCount", total);
        } catch (ServiceException e) {
            log.error("Cant's get all apartments or it's count! " + e.getMessage());
            throw new RuntimeException("Cant's get all apartments or it's count! " + e.getMessage());
        }

        return total;
    }

}
