<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
    <div class="container-sm" style="width: 160px; height: 100%; cursor: pointer;">
        <label for="range" class="form-label" id="rangeValue" style="margin-bottom: 0;">Guests number: ${guests}</label>
        <input type="range" class="form-range" min="1" max="15" id="range" name="guests" value="${guests}">
    </div>
</body>