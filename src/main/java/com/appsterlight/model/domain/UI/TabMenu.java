package com.appsterlight.model.domain.UI;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class TabMenu {
    private HttpServletRequest req;

    public void setUIControls() {
        try {
            String tab = req.getParameter("activeTab");

            tab = (tab != null && !tab.equals("")) ? tab : "0";
            Integer index = Integer.parseInt(tab);
            String name = getNameOfMenuTab(index);
            req.setAttribute(name, "active");
        } catch (NumberFormatException e) {
            log.error("Can't parse tab index from 'activeTab' parameter!", e);
        }
    }
    private String getNameOfMenuTab(Integer index) {
        switch (index) {
            case 0 -> { return "menuHome"; }
            case 1 -> { return "menuApartments"; }
            case 2 -> { return "menuRequests"; }
            case 3 -> { return "menuRequestsForBooking"; }
            case 4 -> { return "menuBookingRequests"; }
            case 5 -> { return "menuBookedList"; }
            case 6 -> { return "menuCabinet"; }
            default -> { return "unknownTab"; }
        }
    }



}
