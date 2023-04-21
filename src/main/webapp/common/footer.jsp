<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<body>

    <nav class="navbar fixed-bottom navbar-light bg-light footer-page-bar">
        <div class="inner-page-bar">
            <nav aria-label="Choose pages" id="pagination-bar" >
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span id="prev-btn" aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach begin="1" end="${pagesCount}" var="item">
                        <c:choose>
                            <c:when test="${item == page}">
                                <li class="page-item active" aria-current="page">
                                    <a class="page-link" href="#">${item}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="#">${item}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span id="next-btn" aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

        <div class="pages-count">
            <select class="form-select" id="pageSize" name="pageSize"
                    onchange="onChangePageSize()" aria-label="Apartments per page:" style="margin-left: 10px;
                                    margin-bottom: 1px;">
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
        <input type="hidden" id="page" name="page" value="${page}">
    </nav>

</body>
