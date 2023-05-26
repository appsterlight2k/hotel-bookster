<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top nav-header-bar">
        <div id="header-bar" class="container-fluid d-flex align-items-center justify-content-start inner-header-bar">
            <h6 style="margin: 0;"><p style="margin: 0;">${headerInfo}</p></h6>
        </div>

        <c:if test="${loggedUser.role == 'ROLE_MANAGER'}">
            <c:set var="isMenuWithTabs" value="nav-tabs" />
        </c:if>

        <c:set var="tabIndex" value="0" />

        <div class="container-fluid inner-header-bar">
            <ul class="nav justify-content-end ms-auto ${isMenuWithTabs}">
                <li class="nav-item">
                    <c:if test="${empty loggedUser || loggedUser.role == 'ROLE_USER'}">
                        <a class="nav-link" aria-current="page" href="controller?action=home">Home</a>
                    </c:if>
                    <c:if test="${loggedUser.role == 'ROLE_MANAGER'}">
                        <a class="nav-link ${param.menuHome} ${menuHome}" aria-current="page" href="controller?action=manager-home&activeTab=${tabIndex}">Home</a>
                    </c:if>
                </li>

                <li class="nav-item">
                    <c:set var="tabIndex" value="${tabIndex + 1}" />
                    <c:if test="${empty loggedUser || loggedUser.role == 'ROLE_USER'}">
                        <a class="nav-link" href="controller?action=apartments">Apartments</a>
                    </c:if>
                    <c:if test="${loggedUser.role == 'ROLE_MANAGER'}">
                        <a class="nav-link ${menuApartments}" href="controller?action=apartments&activeTab=${tabIndex}">Apartments</a>
                    </c:if>
                </li>

                <c:if test="${empty loggedUser}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?action=login">SignIn</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?action=registration">Registration</a>
                    </li>
                </c:if>

                <c:if test="${loggedUser.role == 'ROLE_MANAGER'}">
                    <li class="nav-item">
                        <c:set var="tabIndex" value="${tabIndex + 1}" />
                        <a class="nav-link ${menuRequests}" href="controller?action=manager-get-all-requests&activeTab=${tabIndex}">All Requests</a>
                    </li>
                    <li class="nav-item">
                        <c:set var="tabIndex" value="${tabIndex + 1}" />
                        <a class="nav-link ${menuRequestsForBooking}" href="controller?action=manager-get-requests-for-booking&activeTab=${tabIndex}">Requests for Booking</a>
                    </li>
                    <li class="nav-item">
                        <c:set var="tabIndex" value="${tabIndex + 1}" />
                        <a class="nav-link ${menuBookingRequests}" href="controller?action=manager-get-booking-requests&activeTab=${tabIndex}">Booking Requests</a>
                    </li>
                    <li class="nav-item">
                        <c:set var="tabIndex" value="${tabIndex + 1}" />
                        <a class="nav-link ${menuBookedList}" href="controller?action=manager-get-booked&activeTab=${tabIndex}">Booked List</a>
                    </li>
                </c:if>

                <c:if test="${not empty loggedUser}">
                    <li class="nav-item">
                        <c:set var="tabIndex" value="${tabIndex + 1}" />
                        <div><a class="nav-link ${menuCabinet}" title="personal cabinet" href="controller?action=cabinet&activeTab=${tabIndex}">${loggedUser.email}</a></div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?action=logout">SignOut</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
