<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table table-hover <%--table-primary--%>">
    <thead>
    <tr>
        <td class="table-primary no-select">No.</td>
        <td class="table-primary d-none">id</td>
        <td class="table-primary no-select">first name</td>
        <td class="table-primary no-select">last name</td>
        <td class="table-primary no-select">email</td>

        <td class="table-primary no-select">apart No.</td>
        <td class="table-primary no-select">class</td>
        <td class="table-primary no-select">capacity</td>
        <td class="table-primary no-select">price</td>

        <td class="table-primary no-select">checkIn</td>
        <td class="table-primary no-select">checkOut</td>
        <td class="table-primary no-select">guests</td>
        <td class="table-primary no-select">reservation time</td>
        <td class="table-primary no-select">comments</td>

        <td class="table-primary d-none">firstName</td>
        <td class="table-primary d-none">lastName</td>
        <td class="table-primary d-none">email</td>
        <td class="table-primary d-none">userPhoneNumber</td>
        <td class="table-primary d-none">userDescription</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookings}" var="booking" varStatus="loop">
        <tr><td>${loop.index + offset + 1}</td>
            <td class="d-none">${id}</td>
            <td>${booking.firstName}</td>
            <td>${booking.lastName}</td>
            <td>${booking.email}</td>

            <td>${booking.apartmentNumber}</td>
            <td>${booking.apartmentClass}</td>
            <td>${booking.capacity}</td>
            <td>${booking.price}</td>

            <td>${booking.checkIn}</td>
            <td>${booking.checkOut}</td>
            <td>${booking.adultsNumber}</td>
            <td>${booking.reservationTime}</td>
            <td>${booking.comments}</td>
            <td class="d-none">${booking.firstName}</td>
            <td class="d-none">${booking.lastName}</td>
            <td class="d-none">${booking.email}</td>
            <td class="d-none">${booking.userPhoneNumber}</td>
            <td class="d-none">${booking.userDescription}</td>
        </tr>
        <%--<input type="hidden" id="userId" name="userId" value="${userId}">--%>
    </c:forEach>
    </tbody>
</table>