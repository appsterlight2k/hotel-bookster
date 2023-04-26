<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <div class="container-sm container-centred pages-count">
    <select class="form-select" id="multiDateMode" name="multiDateMode"
            onchange="onChangeMultiDateMode()" aria-label="Date type:" style="cursor: pointer;">
      <c:choose>
        <c:when test="${isMultiDate}">
          <option value="1" selected>From-To date</option>
          <option value="0">From date</option>
        </c:when>
        <c:otherwise>
          <option value="0" selected>From date</option>
          <option value="1">From-To date</option>
        </c:otherwise>
      </c:choose>
    </select>
  </div>

