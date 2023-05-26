<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <div class="container-fluid fixed-top" id="search-panel">
        <ul class="nav justify-content-center">
            <c:if test="${showMultidateControl}">
                <li class="nav-item">
                    <div class="container-sm container-centred checkboxes-bar">
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" id="flexSwitchByPeriod" name="byPeriod"
                                   ${byPeriodChecked} onchange="onChangeSwitch()">
                            <label class="form-check-label" for="flexSwitchByPeriod">By period</label>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <jsp:include page="/common/multidate_control.jsp" />
                </li>
            </c:if>

            <li class="nav-item">
                <div class="container-sm centred container-centred" style="width: 275px;">
                    <input class="form-select" type="text" id="datepicker" placeholder="Select Date Range" data-input
                           data-mode="${dateMode}" data-default-date="${dateRange}" data-min-date="${minDate}">
                </div>
            </li>

            <c:if test="${showGuestsControl}">
                <li class="nav-item">
                    <jsp:include page="/common/guests_control.jsp" />
                </li>
            </c:if>

            <c:if test="${showClassControl}">
                <li class="nav-item">
                    <jsp:include page="apartment_class_control.jsp" />
                </li>
            </c:if>

            <li class="nav-item">
                <jsp:include page="/common/apartment_status_control.jsp" />
            </li>

            <li class="nav-item">
                <input type="hidden" id="search-action" name="action" value="${baseAction}">
                <input type="hidden" id="startDate" name="startDate" value="${startDate}">
                <input type="hidden" id="endDate" name="endDate" value="${endDate}">

                <div class="container-centred">
                    <button type="submit" id="button-search" class="btn btn-light" style="color: #569ff7; border-color: #0d6efd; font-weight: bold;"
                            onclick="submitSearch()">Search</button>
                </div>
            </li>
            <li class="nav-item">
                <div class="container-centred">
                    <div id="header-info" style="margin: 0 auto;">
                        <p style="margin: 0 20px;">${searchResultText} ${totalCount}</p>
                    </div>
                </div>
            </li>
            <c:if test="${loggedUser.role == 'ROLE_USER'}">
                <li>
                    <div class="container-centred">
                        <button type="button" id="button-request" class="btn btn-primary"
                                data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="onRequestClick()">Book Request</button>
                    </div>
                </li>
            </c:if>
            <c:if test="${showDescriptionControl}">
                <li>
                    <div class="container-sm container-centred checkboxes-bar">
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" id="flexShowDesc" name="showDesc"
                            ${showDescChecked} onchange="onChangeShowDescSwitch()" style="cursor: pointer;">
                            <label class="form-check-label" for="flexShowDesc">show description</label>
                        </div>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>

