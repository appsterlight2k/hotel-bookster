<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <c:set var="isHidden" value="d-none" />
    <table class="table table-sm table-striped table-hover" id="apartments-table" style="cursor: pointer;">
        <thead>
            <tr>
                <td class="table-primary ${isHidden}"></td>
                <td class="table-primary">No.</td>
                <td class="table-primary d-none">id</td>
                <td class="table-primary">class</td>
                <td class="table-primary">rooms</td>
                <td class="table-primary">capacity</td>
                <td class="table-primary">price</td>
                <td class="table-primary">status</td>
                <td class="table-primary description-column">description</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${apartments}" var="apartment" varStatus="loop">
                <tr>
                   <td class="${isHidden}"><input type="checkbox" name="selected[]" value="${loop.index + 1}"></td>
                   <td>${loop.index + 1 + ((page - 1) * pageSize)}.</td>
                   <td class="d-none">${apartment.id}</td>
                   <td>${apartment.className}</td>
                   <td>${apartment.roomsCount}</td>
                   <td>${apartment.adultsCapacity}</td>
                   <td>${apartment.price}</td>
                    <td>${apartment.status}</td>
                   <td class="description-column">${apartment.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
