package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Tag;
import com.appsterlight.service.ApartmentPhotosService;
import com.appsterlight.service.ApartmentService;
import com.appsterlight.service.ApartmentTagsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class ShowApartmentAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        ApartmentService apartmentService = appContext.getApartmentService();
        final HttpSession session = req.getSession();

        UserDto user = (UserDto) session.getAttribute("loggedUser");

        try {
            String apartmentId = req.getParameter("apartmentId");
            Long id = Long.parseLong(apartmentId);
            ApartmentDto apartment = DtoUtils.mapApartmentToDto(
                    apartmentService.getApartmentById(id));

            req.setAttribute("apartment", apartment);

            ApartmentPhotosService apartmentPhotosService = appContext.getApartmentPhotosService();
            List<String> photos = apartmentPhotosService.getAllUrlOfPhotosById(id);
            req.setAttribute("photos", photos);

            ApartmentTagsService apartmentTagsService = appContext.getApartmentTagsService();
            List<Tag> allTags = apartmentTagsService.getAllTagsByApartmentId(id);
            req.setAttribute("allTags", allTags);

            List<Tag> basicTags = apartmentTagsService.getAllTagsByApartmentId(id, true);
            req.setAttribute("basicTags", basicTags);

            if (user != null) {
                String guests = req.getParameter("guests");
                req.setAttribute("guests", guests);

                String startDateStr = req.getParameter("startDate");
                LocalDate startDate = LocalDate.parse(startDateStr);
                req.setAttribute("startDate", startDate);

                String endDateStr = req.getParameter("endDate");
                LocalDate endDate = LocalDate.parse(endDateStr);
                req.setAttribute("endDate", endDate);
            }
        } catch (ServiceException e) {
            log.error("Can't get apartment! " + e.getMessage());
            return PagesNames.PAGE_ERROR;
        }

        return PagesNames.PAGE_APARTMENT_SHOW;
    }

}
