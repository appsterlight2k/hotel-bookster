<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top nav-header-bar">
        <div id="header-bar" class="container-fluid inner-header-bar d-flex align-items-center justify-content-start">
            <h6 style="margin: 0;"><p style="margin: 0;">${headerInfo}</p></h6>
        </div>

        <div class="container-fluid inner-header-bar">

            <ul class="nav justify-content-end ms-auto">
                <li class="nav-item" id="menu-home">
                    <a class="nav-link active" aria-current="page" href="controller?action=home">Home</a>
                </li>

                <c:if test="${loggedUser.role != 'ROLE_MANAGER'}">
                    <li class="nav-item" id="menu-apartments">
                        <a class="nav-link" href="controller?action=apartments">Apartments</a>
                    </li>
                </c:if>

                <c:if test="${empty loggedUser}">
                    <li class="nav-item" id="menu-login">
                        <a class="nav-link" href="controller?action=login">SignIn</a>
                    </li>
                    <li class="nav-item" id="menu-registration">
                        <a class="nav-link" href="controller?action=registration">Registration</a>
                    </li>
                </c:if>


                <c:if test="${loggedUser.role == 'ROLE_MANAGER'}">
                    <li class="nav-item" id="menu-get-apartments">
                        <a class="nav-link" href="controller?action=apartments">Apartments</a>
                    </li>
                    <li class="nav-item" id="menu-get-all-requests">
                        <a class="nav-link" href="controller?action=manager-get-all-requests">All Requests</a>
                    </li>
                    <li class="nav-item" id="menu-get-requests-for-booking">
                        <a class="nav-link" href="controller?action=manager-get-requests-for-booking">Requests for Booking</a>
                    </li>
                    <li class="nav-item" id="menu-get-booking-requests">
                        <a class="nav-link" href="controller?action=manager-get-booking-requests">Booking Requests</a>
                    </li>
                    <li class="nav-item" id="menu-get-booked">
                        <a class="nav-link" href="controller?action=manager-get-booked">Booked List</a>
                    </li>
                </c:if>


                <c:if test="${not empty loggedUser}">
                    <li class="nav-item" id="menu-cabinet">
                        <div><a class="nav-link" title="personal cabinet" href="controller?action=cabinet">${loggedUser.email}</a></div>
                    </li>
                    <li class="nav-item" id="menu-logout">
                        <a class="nav-link" href="controller?action=logout">SignOut</a>
                    </li>
                </c:if>
                <input type="hidden" id="menu" name="menu" value="${menu}">
            </ul>
        </div>
    </nav>

