package com.appsterlight.controller.action.impl.post;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.ControllerUtils;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.model.domain.Tag;
import com.appsterlight.service.ApartmentPhotosService;
import com.appsterlight.service.ApartmentService;
import com.appsterlight.service.ApartmentTagsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ShowApartmentAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        ApartmentService apartmentService = appContext.getApartmentService();
        final HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedUser");
        String apartmentId = req.getParameter("apartmentId");
        Long id = Long.parseLong(apartmentId);

        if (user != null) {
            try {
                ApartmentDto apartment = DtoUtils.mapApartmentToDto(
                        apartmentService.getApartmentById(id));
                session.setAttribute("apartment", apartment);

                ApartmentPhotosService apartmentPhotosService = appContext.getApartmentPhotosService();
                List<String> photos = apartmentPhotosService.getAllUrlOfPhotosById(id);
                session.setAttribute("photos", photos);

                ApartmentTagsService apartmentTagsService = appContext.getApartmentTagsService();
                List<Tag> tags = apartmentTagsService.getAllTagsByApartmentId(id);
                session.setAttribute("tags", tags);

                return PagesNames.PAGE_APARTMENT_SHOW;
            } catch (ServiceException e) {
                log.error("Cant's get apartment with id = " + id);
            }
        }

        return PagesNames.PAGE_HOME_GUEST;

    }

}
