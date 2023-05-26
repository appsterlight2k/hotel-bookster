<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="pages-count">
    <select class="form-select" class="pageSize" aria-label="Apartments per page:" style="margin-left: 10px;">
        <c:forEach begin="${minPages}" end="${maxPages}" step="${step}" var="pages">
            <c:choose>
                <c:when test="${pages == pageSize}">
                    <option value="${pages}" selected>${pages}</option>
                </c:when>
                <c:otherwise>
                    <option value="${pages}">${pages}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</div>