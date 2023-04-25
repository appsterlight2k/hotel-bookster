package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentClassDto;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.*;
import com.appsterlight.model.domain.UI.Pagination;
import com.appsterlight.model.domain.UI.Searcher;
import com.appsterlight.service.ApartmentClassService;
import com.appsterlight.service.ApartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.appsterlight.controller.action.factory.constants.ActionName.*;

@Slf4j
public class ApartmentsAction extends FrontAction {
    private final AppContext appContext = AppContext.getAppContext();
    private int oldPageSize;
    private boolean isInitialize = true;
    List<ApartmentClassDto> allApartmentClasses;

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedUser");
        Role role = (user != null) ? Role.valueOf(user.getRole()) : Role.ROLE_GUEST;

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

        if (role == Role.ROLE_MANAGER) {
            req.setAttribute("baseAction", ACTION_APARTMENTS);
            req.setAttribute("showStatuses", true);
            searcher.showDescriptionControl(true);
        } else {
            req.setAttribute("baseAction", ACTION_APARTMENTS);
            if (role == Role.ROLE_USER)
                req.setAttribute("secondaryAction", ACTION_BOOKING);
            searcher.getPeriodCheckboxState(isInitialize);
        }

        searcher.setSearchResultText("Apartments found");
        searcher.setHeaderInfo("Apartments");
        isInitialize = false;

        return PagesNames.PAGE_APARTMENTS;
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
