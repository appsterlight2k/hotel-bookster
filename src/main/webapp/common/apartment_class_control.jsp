<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
    <div class="container container-centred">
        <select class="form-select" name="apartmentClass" onchange="SubmitSearch()"
                aria-label="Choose Apartment Class:" style="cursor: pointer;">
            <c:if test="${not empty apartmentClasses}">
                <c:choose>
                    <c:when test="${chosenClass == 0}">
                        <option value="0" selected>Any Class</option>
                    </c:when>
                    <c:otherwise>
                        <option value="0">Any Class</option>
                    </c:otherwise>
                </c:choose>

                <c:forEach items="${apartmentClasses}" var="apartClass">
                    <c:choose>
                        <c:when test="${chosenClass == apartClass.id}">
                            <option value="${apartClass.id}" selected> ${apartClass.name} </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${apartClass.id}"> ${apartClass.name} </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
        </select>
    </div>
</body>

