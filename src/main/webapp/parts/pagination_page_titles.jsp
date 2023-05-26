<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="inner-page-bar" style="height: 100%;">
    <nav aria-label="Choose pages" class="pagination-bar">
        <ul class="container-sm container-centred pagination">
            <li class="page-item">
                <a id="prev-btn" class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
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
                <a id="next-btn" class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>