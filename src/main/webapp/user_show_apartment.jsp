<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Apartments</title>

    <style>
        .error { color: red; }
        .check { color: green; }
        .tags_container {
            width: 500px;
            margin: auto;
            float: left;
            border: blueviolet;
        }
        .carousel {
            height: 400px;
            width: 400px;
        }


    </style>
</head>
<body>
    <jsp:include page="/common/navbar.jsp" />

    <div class="container mt-5">
        <h5>Apartment details</h5> <br>
        <c:if test="${not empty apartment}">
            <div class="card <%--text-center--%> text-center">
                <div class="card-header">
                    Apartment ${apartment.className}
                </div>

                <div class="card-body">
                    <h5 class="card-title">Apartment</h5>

                    <div class="container d-flex">
                        <div class="container">
                            <c:if test="${not empty photos}">
                                <div class="d-flex justify-content-center mt-5">
                                    <div id="carouselInterval" class="carousel slide d-flex justify-content-center" data-bs-ride="carousel">
                                        <div class="carousel-inner">
                                            <c:forEach items="${photos}" var="photo" varStatus="loop">
                                                <c:if test="${loop.index == 0}">
                                                    <div class="carousel-item active" data-bs-interval="5000">
                                                        <img src="${photo}" class="d-block w-100 img-fluid" alt="image" height="300" onclick="openModal(this);">
                                                    </div>
                                                </c:if>
                                                <c:if test="${loop.index > 0}">
                                                    <div class="carousel-item" data-bs-interval="3000">
                                                        <img src="${photo}" class="d-block w-100" alt="image" height="300" onclick="openModal(this);">
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <br>
                                        </div>

                                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselInterval" data-bs-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Previous</span>
                                        </button>
                                        <button class="carousel-control-next" type="button" data-bs-target="#carouselInterval" data-bs-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Next</span>
                                        </button>
                                    </div>
                                </div>
                            </c:if>
                        </div>

                        <div class="container">
                            <c:forEach items="${tags}" var="tag">
                            <span class="check">✓<span> ${tag.name}<br>
                            </c:forEach>
                        </div>
                    </div>

                    <br>

                    <div>
                        <b>roomsCount:</b>  ${apartment.roomsCount} <br>
                        <b>class:</b> ${apartment.className} <br>
                        <b>adults capacity:</b> ${apartment.adultsCapacity} <br>
                        <b>childrenCapacity:</b> ${apartment.childrenCapacity} <br>
                        <b>Description:</b> <p class="card-text">${apartment.description}.</p>
                        <b>price:</b> ${apartment.price} <br><br>
                    </div>

                    <br>

                    <form action="controller" method="get">
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel">Application</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h6 id="modal-summary">You make request for Apartment for ${guests} person(s) between ${startDate} and ${endDate} </h6>
                                        <div class="mb-3">
                                            <label for="comments" class="col-form-label">Comments:</label>
                                            <textarea class="form-control" id="comments" name="comments"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-success" data-bs-dismiss="modal">Book it!</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="action" value="booking">
                        <input type="hidden" name="apartmentId" value="${apartment.id}">
                        <input type="hidden" name="range" value="${guests}">
                        <input type="hidden" name="startDate" value="${startDate}">
                        <input type="hidden" name="endDate" value="${endDate}">
                        <input type="hidden" name="isRequestOnly" value="false">

                        <button type="button" id="button-request" class="btn btn-primary"
                                data-bs-toggle="modal" data-bs-target="#staticBackdrop">Confirm</button>
                    </form>
                </div>

            </div>
        </c:if>
    </div>

    <script>


    </script>

<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
