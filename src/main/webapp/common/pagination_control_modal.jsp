<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <div class="inner-page-bar" style="height: 100%;">
    <nav aria-label="Choose pages" class="pagination-bar">
      <ul class="pagination" style="margin-bottom: 0;">
        <%--generate content dynamically--%>
      </ul>
    </nav>
  </div>

  <jsp:include page="/parts/pagination_page_size.jsp" />