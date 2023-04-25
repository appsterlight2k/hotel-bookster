package com.appsterlight.model.domain.UI;

import com.appsterlight.controller.action.utils.DtoUtils;
import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentClassDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.ApartmentClass;
import com.appsterlight.service.ApartmentClassService;
import com.appsterlight.service.ApartmentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class Searcher {
    private static final AppContext appContext = AppContext.getAppContext();
    private final HttpServletRequest req;
    private String minDate;
    private final Boolean isDateModeChangeable;
    private final List<String> statuses = new ArrayList<>(List.of("free", "booked", "busy", "unavailable"));

    public Searcher(HttpServletRequest req, Boolean isDateModeChangeable) {
        this.req = req;
        this.isDateModeChangeable = isDateModeChangeable;
    }

    //no parameters: attribute minDate = today;
    //some string like 'empty': attribute minDate = "";
    //other: attribute minDate = parameter minDate;
    public void setMinDate(String... minDateArg) {
        LocalDate today = LocalDate.now();
        minDate = minDateArg.length > 0 ? "" : today.toString();
        req.setAttribute("minDate", minDate);
    }
    public Boolean setMultiDateMode(LocalDate... dates) {
        String datesRange;
        boolean isMultiDate = dates.length > 1 && dates[1] != null;

        if (isDateModeChangeable) {
            String byPeriod = req.getParameter("byPeriod");
            String multiDateStr = req.getParameter("multiDateMode");
            if (multiDateStr != null) {
                isMultiDate = booleanFromString(multiDateStr);
            }
        }

        String dateMode = isMultiDate ? "range" : "single";
        datesRange = (dateMode.equals("range")) ? getDatesRange(dates) : getDatesRange(dates[0]);

        req.setAttribute("dateRange", datesRange);
        req.setAttribute("dateMode", dateMode);
        req.setAttribute("isMultiDate", isMultiDate);

        return isMultiDate;
    }

    public List<ApartmentClassDto> getApartmentClasses() {
        List<ApartmentClassDto> result;

        try {
            ApartmentClassService apartmentClassService = appContext.getApartmentClassService();
            List<ApartmentClass> allApartmentClasses = apartmentClassService.getAllApartmentClasses();
            result = DtoUtils.mapApartmentClassListToDtoList(allApartmentClasses);


        } catch (ServiceException e) {
            log.error("Cant's get all Apartment Classes! " + e.getMessage());
            throw new RuntimeException("Cant's get all Apartment Classes! " + e.getMessage());
        }

        return result;
    }

    public void setApartmentClasses(List<ApartmentClassDto> list) {
        req.setAttribute("apartmentClasses", list);
    }

    public String getApartmentClass() {
        String apartmentClass = req.getParameter("apartmentClass");
        req.setAttribute("chosenClass", apartmentClass);

        return apartmentClass;
    }

    public String getApartmentStatus() {
        String status = req.getParameter("status");
        if (status == null) status = "0";
        req.setAttribute("chosenStatus", status);
        req.setAttribute("chosenStatus", status);
        req.setAttribute("apartmentStatuses", statuses);

        return (status.equals("0")) ? "%" : statuses.get(Integer.parseInt(status) - 1);
    }

    private String getDatesRange(LocalDate... dates) {
        StringBuilder dateRange = new StringBuilder();

        dateRange.append(dates[0]);
        for (int i = 1; i < dates.length; i++) {
            dateRange.append(" to ").append(dates[i]);
        }

        return dateRange.toString();
    }
    public LocalDate getStartDay() {
        LocalDate startDate;
        LocalDate today = LocalDate.now();
        String startDateReq = req.getParameter("startDate");

        if (startDateReq != null) {
            startDate = LocalDate.parse(startDateReq);
        } else {
            startDate = today;
        }
        req.setAttribute("startDate", startDate);

        return startDate;
    }

    public LocalDate getEndDay() {
        LocalDate endDate;
        LocalDate currentDay = LocalDate.now();
        String endDateReq = req.getParameter("endDate");

        if (endDateReq != null) {
            endDate = endDateReq.equals("") ? null : LocalDate.parse(endDateReq);
        } else {
            endDate = currentDay;
        }
        req.setAttribute("endDate", endDate);

        return endDate;
    }

    public int getGuests() {
        int guests = 1;
        String guestsStr = req.getParameter("guests");

        if (guestsStr != null) {
            guests = Integer.parseInt(guestsStr);
        } else {
            String guestsFromRequest = (String) req.getAttribute("guests");
            if (guestsFromRequest != null) {
                guests = Integer.parseInt(guestsFromRequest);
            }
        }

        req.setAttribute("guests", guests);

        return guests;
    }

    public void showGuestsControl(Boolean showControl) {
        req.setAttribute("showGuestsControl", showControl);
    }

    public void showMultiDateControl(Boolean showControl) {
        req.setAttribute("showMultidateControl", showControl);
    }

    public void showClassControl(Boolean showControl) {
        req.setAttribute("showClassControl", showControl);
    }

    public void showDescriptionControl(Boolean showControl) {
        req.setAttribute("showDescriptionControl", showControl);
    }

    public String getPeriodCheckboxState(Boolean isInitialize) {
        String result = isInitialize ? "" : (req.getParameter("byPeriod") != null ? "checked" : "");
        req.setAttribute("byPeriodChecked", result);
        return result;
    }

    public String getShowDescCheckboxState(Boolean isInitialize) {
        String result = isInitialize ? "" : (req.getParameter("showDesc") != null ? "checked" : "");
        req.setAttribute("showDescChecked", result);
        return result;
    }

    public void setSearchResultText(String text) {
        req.setAttribute("searchResultText", text + ":");
    }

    public void setHeaderInfo(String headerInfo) {
        req.setAttribute("headerInfo", headerInfo);
    }


    private boolean booleanFromString(String arg) {
        return !arg.equals("0");
    }


}
