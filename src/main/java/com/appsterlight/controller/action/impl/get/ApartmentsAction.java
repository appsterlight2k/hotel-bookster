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

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        ApartmentService apartmentService = appContext.getApartmentService();

        final HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedUser");
        if (user != null) {
            try {
                List<ApartmentDto> allApartments = new ArrayList<>();

                String range = req.getParameter("range");
                Integer guests = (range != null) ? Integer.parseInt(range) : 1;

                LocalDate checkIn = LocalDate.now(); //.plusDays(1);
                LocalDate checkOut = checkIn;

                String startDate = req.getParameter("startDate");
                String endDate = req.getParameter("endDate");

                if (startDate != null && endDate != null) {
                    checkIn = LocalDate.parse(startDate);
                    checkOut = LocalDate.parse(endDate);

                    allApartments = DtoUtils.mapApartmentListToDtoList(
                            apartmentService.getAllFreeApartments(guests, checkIn, checkOut));
                    req.setAttribute("apartmentsCount", "Apartments found: " + allApartments.size());
                }

                req.setAttribute("startDate", checkIn.toString());
                req.setAttribute("endDate", checkOut.toString());
                req.setAttribute("guests", guests);
                req.setAttribute("apartments", allApartments);

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
