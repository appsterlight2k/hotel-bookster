<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table table-hover" style="cursor: pointer;">
    <thead>
    <tr>
        <td class="table-primary no-select">No.</td>
        <td class="table-primary d-none">id</td>
        <td class="table-primary no-select">first name</td>
        <td class="table-primary no-select">last name</td>
        <td class="table-primary no-select">email</td>
        <td class="table-primary no-select">class</td>
        <td class="table-primary no-select">guests</td>
        <td class="table-primary no-select">checkIn</td>
        <td class="table-primary no-select">checkOut</td>
        <td class="table-primary no-select">reservation time</td>
        <td class="table-primary no-select">comments</td>
        <td class="table-primary d-none">phone number</td>
        <td class="table-primary d-none">info</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookings}" var="booking" varStatus="loop">
        <tr><td>${loop.index + offset + 1}</td>
            <td id="user-id" class="d-none">${booking.id}</td>
            <td id="user-firstname">${booking.firstName}</td>
            <td id="user-lastname">${booking.lastName}</td>
            <td id="user-email">${booking.email}</td>
            <td>${booking.apartmentClass}</td>
            <td>${booking.adultsNumber}</td>
            <td>${booking.checkIn}</td>
            <td>${booking.checkOut}</td>
            <td>${booking.reservationTime}</td>
            <td>${booking.comments}</td>
            <td id="user-phone" class="d-none">${booking.userPhoneNumber}</td>
            <td id="user-description" class="d-none">${booking.userDescription}</td>
        </tr>
        <%--<input type="hidden" id="userId" name="userId" value="${userId}">--%>
    </c:forEach>
    </tbody>
</table>