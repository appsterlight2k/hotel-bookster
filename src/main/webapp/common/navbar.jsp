<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <style>

    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <div class="container-fluid">
            <ul class="nav justify-content-end ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="controller?action=home"><b>Home</b></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=apartments"><b>Apartments</b></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=registration"><b>Registration</b></a>
        <%--            <a class="nav-link" href="registration.jsp">Registration</a>--%>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=manager_requests"><b>Booking Requests</b></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=login"><b>SignIn</b></a>
                </li>

                <li class="nav-item">
                    <div><a class="nav-link" href="controller?action=cabinet"><b>${sessionScope.loggedUser.email}</b></a></div>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="controller?action=logout"><b>SignOut</b></a>
                </li>

               <%-- <li class="nav-item">
                    <a class="nav-link disabled">Disabled</a>
                </li>--%>
            </ul>
        </div>
    </nav>
</body>
</html>
