<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <style>
        .header-bar {
            height: 38px;
            padding: 0;
        }
        .inner-header-bar {
            height: 100%;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top header-bar">
        <div class="container-fluid inner-header-bar">
            <ul class="nav justify-content-end ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="controller?action=home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=apartments">Apartments</a>
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
                        <a class="nav-link" href="controller?action=manager_requests">Booking Requests</a>
                    </li>
                </c:if>

                <c:if test="${not empty loggedUser}">
                    <li class="nav-item">
                        <div><a class="nav-link" href="controller?action=cabinet">${loggedUser.email}</a></div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?action=logout">SignOut</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</body>
</html>
