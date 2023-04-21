<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <table class="table table-sm table-striped table-hover" id="apartments-table">
        <thead>
            <tr>
                <td class="table-primary">#</td>
                <td class="table-primary">class</td>
                <td class="table-primary">rooms</td>
                <td class="table-primary">capacity</td>
                <td class="table-primary">price</td>
                <td class="table-primary description-column">description</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${apartments}" var="apartment" varStatus="loop">
                <tr>
                   <td>${loop.index + 1 + ((page - 1) * pageSize)}.</td>
                   <td>${apartment.className}</td>
                   <td>${apartment.roomsCount}</td>
                   <td>${apartment.adultsCapacity}</td>
                   <td>${apartment.price}</td>
                   <td class="description-column">${apartment.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
