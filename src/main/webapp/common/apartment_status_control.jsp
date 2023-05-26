<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
  <c:if test="${showStatuses}">
    <div class="container container-centred">
      <select class="form-select" name="status" onchange="submitSearch()"
              aria-label="Choose Apartment Status:" style="cursor: pointer;">
        <c:if test="${not empty apartmentStatuses}">
          <c:choose>
            <c:when test="${chosenStatus == 0}">
              <option value="0" selected>Any Status</option>
            </c:when>
            <c:otherwise>
              <option value="0">Any Status</option>
            </c:otherwise>
          </c:choose>

          <c:forEach items="${apartmentStatuses}" var="status" varStatus="loop">
            <c:choose>
              <c:when test="${chosenStatus == loop.index + 1}">
                <option value="${loop.index + 1}" selected> ${status} </option>
              </c:when>
              <c:otherwise>
                <option value="${loop.index + 1}"> ${status} </option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </c:if>
      </select>
    </div>
  </c:if>

</body>