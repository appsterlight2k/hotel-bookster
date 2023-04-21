package com.appsterlight.model.domain;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Searcher {
    private final HttpServletRequest req;
    private String minDate;
    private final Boolean isDateModeChangeable;

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
