<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <nav class="navbar fixed-bottom navbar-light bg-light footer-page-bar">
        <jsp:include page="pagination_control.jsp" />
        <input type="hidden" id="page" name="page" value="${page}">
    </nav>

