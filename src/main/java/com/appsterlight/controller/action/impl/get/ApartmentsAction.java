package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.utils.ControllerUtils;
import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.constants.PagesNames;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.service.ApartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ApartmentsAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        ApartmentService apartmentService = appContext.getApartmentService();

        final HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedUser");

        try {
            List<ApartmentDto> allApartments = DtoUtils.mapApartmentListToDtoList(
                    apartmentService.getAllApartments());
            session.setAttribute("apartments", allApartments);
        } catch (ServiceException e) {
            log.error("Cant's get all apartments!");
        }

        return  user != null ?
                ControllerUtils.getApartmentsPageByRole(user.getRole()) :
                PagesNames.PAGE_APARTMENTS_GUEST;
    }
}
