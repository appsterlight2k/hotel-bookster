package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentClassDto;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.model.domain.ApartmentClass;
import com.appsterlight.model.domain.PaginationByRequest;
import com.appsterlight.model.domain.Searcher;
import com.appsterlight.service.ApartmentClassService;
import com.appsterlight.service.ApartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import static com.appsterlight.controller.action.factory.constants.ActionName.ACTION_APARTMENTS;
import static com.appsterlight.controller.action.factory.constants.ActionName.ACTION_BOOKING;

@Slf4j
public class ApartmentsAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();
    private final ApartmentClassService apartmentClassService = appContext.getApartmentClassService();
    private int oldPageSize;
    private boolean isInitialize = true;

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedUser");
        String role = (user != null) ? user.getRole() : "ROLE_GUEST";

        try {
            Searcher searcher = new Searcher(req, false);
            searcher.setMinDate(); //today
            LocalDate startDate = searcher.getStartDay();
            LocalDate endDate = searcher.getEndDay();
            searcher.setMultiDateMode(startDate, endDate);
            searcher.showGuestsControl(true);

            Integer guests = searcher.getGuests();
            searcher.getPeriodCheckboxState(isInitialize);

            PaginationByRequest page = new PaginationByRequest(req);
            int pageSize = page.getPageSize();
            page.setPage(oldPageSize);
            Integer offset = page.getOffset();

            String apartmentClass = req.getParameter("apartmentClass");
            Integer totalCount = getAllApartments(req, apartmentClass, guests, startDate, endDate,
                    offset, pageSize);
            req.setAttribute("chosenClass", apartmentClass);

            page.setPagesCount(totalCount);
            page.setPagesAttributes();

            List<ApartmentClass> allClasses = apartmentClassService.getAllApartmentClasses();
            List<ApartmentClassDto> allApartmentClasses = DtoUtils.mapApartmentClassListToDtoList(allClasses);
            req.setAttribute("apartmentClasses", allApartmentClasses);

            oldPageSize = pageSize;

            isInitialize = false;
            req.setAttribute("baseAction", ACTION_APARTMENTS);
            if (role.equalsIgnoreCase("ROLE_USER"))
                req.setAttribute("secondaryAction", ACTION_BOOKING);
            searcher.setSearchResultText("Apartments found");
            searcher.setHeaderInfo("Apartments");
       /* } catch (ServiceException e) {
            log.error("Cant's get all apartments! " + e.getMessage());
            throw new RuntimeException("Cant's get all apartments! " + e.getMessage());*/
        } catch (Exception e) {
            log.error("Can't parse date of Checkin date, Checkout date, " +
                    "Apartment Class or Apartments per page! " + e.getMessage());
            throw new RuntimeException("Cant's get all apartments! " + e.getMessage());
        }
        return PagesNames.PAGE_APARTMENTS;

    }

    private Integer getAllApartments(HttpServletRequest req, String apartmentClass, Integer guests,
                                     LocalDate startDate, LocalDate endDate, Integer offset, Integer pageSize) {
        final ApartmentService apartmentService = appContext.getApartmentService();
        Integer total = 0;
        List<Apartment> apartments;
        List<ApartmentDto> result;

        try {
            if ((apartmentClass == null) || apartmentClass.equals("0") ||
                    apartmentClass.equalsIgnoreCase("Any Class")) {
                apartments = apartmentService.getAllFreeApartments(guests, startDate, endDate, offset, pageSize);
                total = apartmentService.getCountOfAllFree(guests, startDate, endDate);
            } else {
                apartments = apartmentService.getAllFreeApartments(guests, startDate, endDate,Integer.parseInt(apartmentClass),
                        offset, pageSize);
                total = apartmentService.getCountOfAllFree(guests, startDate, endDate, Integer.parseInt(apartmentClass));
            }
            result = DtoUtils.mapApartmentListToDtoList(apartments);
            req.setAttribute("apartments", result);
            req.setAttribute("totalCount", total);
        } catch (ServiceException e) {
            log.error("Cant's get all apartments! " + e.getMessage());
            throw new RuntimeException("Cant's get all apartments! " + e.getMessage());
        }

        return total;
    }

}
