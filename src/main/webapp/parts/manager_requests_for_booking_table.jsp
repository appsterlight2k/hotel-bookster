<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="isHidden" value="d-none" />

<table class="table table-hover" id="table-req-for-booking" style="cursor: pointer;">
    <thead>
    <tr>
        <td class="table-primary no-select ${isHidden}"></td>
        <td class="table-primary no-select">No.</td>
        <td class="table-primary no-select ${isHidden}">id</td>
        <td class="table-primary no-select">first name</td>
        <td class="table-primary no-select">last name</td>
        <td class="table-primary no-select">email</td>
        <td class="table-primary no-select">class</td>
        <td class="table-primary no-select">guests</td>
        <td class="table-primary no-select">checkIn</td>
        <td class="table-primary no-select">checkOut</td>
        <td class="table-primary no-select">reservation time</td>
        <td class="table-primary no-select">comments</td>
        <td class="table-primary no-select ${isHidden}">phone number</td>
        <td class="table-primary no-select ${isHidden}">info</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookings}" var="booking" varStatus="loop">
        <tr>
            <td class="${isHidden}"><input type="checkbox" name="selected[]" value="${loop.index + 1}"></td>
            <td>${loop.index + 1 + ((page - 1) * pageSize)}</td>
            <td id="booking-id" class="${isHidden}">${booking.id}</td>
            <td id="user-firstname">${booking.firstName}</td>
            <td id="user-lastname">${booking.lastName}</td>
            <td id="user-email">${booking.email}</td>
            <td>${booking.apartmentClass}</td>
            <td>${booking.adultsNumber}</td>
            <td>${booking.checkIn}</td>
            <td>${booking.checkOut}</td>
            <td>${booking.reservationTime}</td>
            <td>${booking.comments}</td>
            <td id="user-phone" class="${isHidden}">${booking.userPhoneNumber}</td>
            <td id="user-description" class="${isHidden}">${booking.userDescription}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>