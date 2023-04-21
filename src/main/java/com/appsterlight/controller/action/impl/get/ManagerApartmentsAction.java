package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentClassDto;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.model.domain.ApartmentClass;
import com.appsterlight.model.domain.PaginationByRequest;
import com.appsterlight.model.domain.Searcher;
import com.appsterlight.service.ApartmentClassService;
import com.appsterlight.service.ApartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import static com.appsterlight.controller.action.factory.constants.ActionName.ACTION_MANAGER_ALL_APARTMENTS;

@Slf4j
public class ManagerApartmentsAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();
    private final ApartmentClassService apartmentClassService = appContext.getApartmentClassService();
    private final ApartmentService apartmentService = appContext.getApartmentService();

    private Integer oldPageSize;

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {

            Searcher searcher = new Searcher(req, false);
            searcher.setMinDate(); //today
            LocalDate startDate = searcher.getStartDay();
            LocalDate endDate = searcher.getEndDay();
            searcher.setMultiDateMode(startDate, endDate);

            searcher.showGuestsControl(true);
            Integer guests = searcher.getGuests();
            searcher.showDescriptionControl(true);

            PaginationByRequest page = new PaginationByRequest(req);
            Integer pageSize = page.getPageSize();
            page.setPage(oldPageSize);
            Integer offset = page.getOffset();

            String apartmentClass = req.getParameter("apartmentClass");
            Integer totalCount = getAllApartments(req, apartmentClass, guests, startDate, endDate,
                    offset, pageSize);

        try {
            List<ApartmentClass> allClasses = apartmentClassService.getAllApartmentClasses();
            List<ApartmentClassDto> allApartmentClasses = DtoUtils.mapApartmentClassListToDtoList(allClasses);
            req.setAttribute("apartmentClasses", allApartmentClasses);
            req.setAttribute("chosenClass", apartmentClass);
        } catch (ServiceException e) {
            log.error("Cant's get all apartments! " + e.getMessage());
            throw new RuntimeException("Cant's get all apartments! " + e.getMessage());
        }

        oldPageSize = pageSize;

        //set pagination parameters:
        page.setPagesCount(totalCount);
        page.setPagesAttributes();

        req.setAttribute("baseAction", ACTION_MANAGER_ALL_APARTMENTS);
        searcher.setSearchResultText("Apartments found");
        searcher.setHeaderInfo("Apartments");

        return PagesNames.PAGE_APARTMENTS;
    }

    private Integer getAllApartments(HttpServletRequest req, String apartmentClass, Integer guests,
                                     LocalDate startDate, LocalDate endDate, Integer offset,
                                     Integer pageSize) {
        Integer total;
        List<Apartment> apartments;
        try {
            if ((apartmentClass == null)
                    || apartmentClass.equals("0")
                    || apartmentClass.equalsIgnoreCase("Any Class")) {
                apartments = apartmentService.getAllFreeApartments(guests, startDate, endDate, offset, pageSize);
                total = apartmentService.getCountOfAllFree(guests, startDate, endDate);
            } else {
                apartments = apartmentService.getAllFreeApartments(guests, startDate, endDate,
                        Integer.parseInt(apartmentClass), offset, pageSize);
                total = apartmentService.getCountOfAllFree(guests, startDate, endDate, Integer.parseInt(apartmentClass));
            }
            List<ApartmentDto> result = DtoUtils.mapApartmentListToDtoList(apartments);

            req.setAttribute("apartments", result);
            req.setAttribute("totalCount", total);
        } catch (ServiceException e) {
            log.error("Cant's get all apartments! " + e.getMessage());
            throw new RuntimeException("Cant's get all apartments! " + e.getMessage());
        }
        return total;
    }



}
