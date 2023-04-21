<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <div class="container-fluid fixed-top" id="search-panel">
        <ul class="nav justify-content-center" style="height: 48px;">
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

            <c:if test="${empty loggedUser || loggedUser.role == 'ROLE_USER'}">
                <li>
                    <div class="container container-centred">
                        <select class="form-select" name="apartmentClass" onchange="SubmitSearch()"
                                aria-label="Choose Apartment Class:">
                            <c:if test="${not empty apartmentClasses}">
                                <c:choose>
                                    <c:when test="${chosenClass == 0}">
                                        <option value="0" selected>Any Class</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">Any Class</option>
                                    </c:otherwise>
                                </c:choose>

                                <c:forEach items="${apartmentClasses}" var="apartClass">
                                    <c:choose>
                                        <c:when test="${chosenClass == apartClass.id}">
                                            <option value="${apartClass.id}" selected> ${apartClass.name} </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${apartClass.id}"> ${apartClass.name} </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </li>
            </c:if>

            <li class="nav-item">
                <input type="hidden" id="search-action" name="action" value="${baseAction}">
                <input type="hidden" id="startDate" name="startDate" value="${startDate}">
                <input type="hidden" id="endDate" name="endDate" value="${endDate}">


                <div class="container-centred">
                    <button type="submit" id="button-search" class="btn btn-light" style="color: #569ff7; border-color: #0d6efd; font-weight: bold;"
                            onclick="onSearchClick()">Search</button>
                </div>

            </li>
            <li class="nav-item">
                <div class="container-centred">
                    <div style="width: 250px; margin: 0 auto;">
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
                            ${showDescChecked} onchange="onChangeShowDescSwitch()">
                            <label class="form-check-label" for="flexShowDesc">show description</label>
                        </div>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>

