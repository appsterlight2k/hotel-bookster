package com.appsterlight.controller.action.impl.get;

import com.appsterlight.controller.action.FrontAction;
import com.appsterlight.controller.action.factory.ActionFactory;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.OfferedAparment;
import com.appsterlight.service.BookingService;
import com.appsterlight.service.OfferedApartmentsService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static com.appsterlight.controller.action.factory.constants.ActionName.ACTION_MANAGER_REQUESTS_FOR_BOOKING;
import static com.appsterlight.controller.constants.PagesNames.JSON_RESPONSE;

@Slf4j
public class ManagerOfferApartmentsAction extends FrontAction {
    private static final AppContext appContext = AppContext.getAppContext();
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        return doPost(req, resp);
    }

    private String doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = ACTION_MANAGER_REQUESTS_FOR_BOOKING;

        OfferedApartmentsService offeredApartmentsService = appContext.getOfferedApartmentsService();
        BookingService bookingService = appContext.getBookingService();
        List<Long> apartmentsId;
        BufferedReader reader = null;
        try {
            reader = req.getReader();
            Gson gson = new Gson();
            DataObject data = gson.fromJson(reader, DataObject.class);
            apartmentsId = data.getApartmentsId();
            if (!apartmentsId.isEmpty()) {
                for (Long apartmentId : apartmentsId) {
                    OfferedAparment offer = new OfferedAparment();
                    offer.setBookingId(data.getBookingId());
                    offer.setApartmentId(apartmentId);
                    offer.setMessage(data.getMessage());
                    offeredApartmentsService.add(offer);
                }
                Long bookingId = data.bookingId;
                bookingService.setIsOffered(bookingId, true);
            }
        } catch (ServiceException e) {
            log.error("Can't add all Offered Apartments into table in class " + this.getClass().getSimpleName() + "! " + e);
        } catch (IOException e) {
            log.error("Error! Can't get JSON wi th Apartments id's from Request! ", e);
        }

      return JSON_RESPONSE;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class DataObject {
        private List<Long> apartmentsId;
        private Long bookingId;
        private String message;
    }

}
