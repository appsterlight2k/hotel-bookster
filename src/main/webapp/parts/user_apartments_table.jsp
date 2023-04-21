<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach items="${apartments}" var="apartment" varStatus="loop">
  <div class="card text-center card-container">
    <div class="card-header" style="border-radius: 22px 22px 0 0; background-color: #569ff752;">
      Result: #${loop.index + 1 + ((page - 1) * pageSize)}
    </div>
    <div>
      <h6>Class: ${apartment.className}</h6>
    </div>
  <div class="card-body" style="display: flex;">
    <div>
      <img src="${apartment.mainPhotoUrl}" alt="image" height="200" ><br>
    </div>
    <div style="text-align: left; width: 100%; margin-left: 15px;">
      <b>rooms count: </b>${apartment.roomsCount} <br>
      <b>adults capacity: </b>${apartment.adultsCapacity} <br>
      <b>children capacity: </b>${apartment.childrenCapacity} <br>
      <b>price: </b>${apartment.price} <br><br>
      <b>Description:</b> <p class="card-text">${apartment.description}.</p>
    </div>

    </div>
    <div style="display: flex; align-items: center; justify-content: center; margin-bottom: 10px;">
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/controller?action=get-apartment&apartmentId=${apartment.id}&startDate=${startDate}&endDate=${endDate}&guests=${guests}" role="button">Details...</a>
    </div>
  </div>
  </c:forEach>