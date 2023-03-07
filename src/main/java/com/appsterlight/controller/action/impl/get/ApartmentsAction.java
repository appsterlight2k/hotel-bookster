package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.ControllerUtils;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.service.ApartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ApartmentsAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();
    private String chosenStartDate;
    private String chosenEndDate;


    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        ApartmentService apartmentService = appContext.getApartmentService();

        final HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedUser");
        if (user != null) {
            try {
                List<ApartmentDto> allApartments = new ArrayList<>();

                String range = req.getParameter("range");
                Integer guests = 1;

                if (range != null) {
                    guests = Integer.parseInt(range);
                } else {
                    String guestsFromSession = (String) session.getAttribute("guests");
                    if (guestsFromSession != null) {
                        guests = Integer.parseInt(guestsFromSession);
                    }
                }

                LocalDate currentDay = LocalDate.now();

                String startDateReq = req.getParameter("startDate");
                String endDateReq = req.getParameter("endDate");

                if (startDateReq != null && endDateReq != null && !startDateReq.equals("")  && !endDateReq.equals("")) {
                    chosenStartDate = startDateReq;
                    chosenEndDate = endDateReq;
                } else {
                    if (chosenStartDate == null || chosenEndDate == null) {
                        chosenStartDate = currentDay.toString();
                        chosenEndDate = chosenStartDate;
                    }
                }

                /*req.setAttribute("startDate", chosenStartDate);
                req.setAttribute("endDate", chosenEndDate);*/
                session.setAttribute("startDate", chosenStartDate);
                session.setAttribute("endDate", chosenEndDate);

//                ((checkInStr != null && checkOutStr != null) || (checkInStr != "" && checkOutStr != ""))  ||
/*
                if (startDate != null && endDate != null) {
                    currentDay = LocalDate.parse(startDate);
                    currentDay = LocalDate.parse(endDate);*/

                    allApartments = DtoUtils.mapApartmentListToDtoList(
                            apartmentService.getAllFreeApartments(guests, LocalDate.parse(chosenStartDate),
                                    LocalDate.parse(chosenEndDate)));
//                    req.setAttribute("apartmentsCount", "Apartments found: " + allApartments.size());
//                }


                /*req.setAttribute("guests", guests);
                req.setAttribute("apartments", allApartments);*/
//                session.setAttribute("guests", guests);
                session.setAttribute("guests", guests);
                req.setAttribute("guests", guests);
                session.setAttribute("apartments", allApartments);

                // req.setAttribute("apartments", allApartments);   change in all places
            } catch (ServiceException e) {
                log.error("Cant's get all apartments! " + e.getMessage());
                throw new RuntimeException("Cant's get all apartments! " + e.getMessage());
            } catch (Exception e) {
                log.error("Can't parse date of checkin or checkout! " + e.getMessage());
                throw new RuntimeException("Cant's get all apartments! " + e.getMessage());
            }
            return ControllerUtils.getApartmentsPageByRole(user.getRole());
        }
        return PagesNames.PAGE_APARTMENTS_GUEST;
    }

}
