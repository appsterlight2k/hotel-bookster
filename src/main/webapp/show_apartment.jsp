<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="css/main.css">

    <title>Apartments</title>

    <style>
        body {
            padding-top: 0;
        }
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

        .accordion-button:after,
        .accordion-button.collapsed:before {
            display: inline-block;
            vertical-align: middle;
            margin-right: 5px;
        }

        .accordion-button.collapsed:after,
        .accordion-button:not(.collapsed):after {
            display: inline-block;
            vertical-align: middle;
            margin-left: 5px;
        }

    </style>
</head>
<body>
    <jsp:include page="/common/header.jsp" />

    <div class="container mt-5 d-flex flex-column">
        <div class="container d-flex justify-content-center">
            <h6>Apartment details:</h6>
        </div>
        <c:if test="${not empty apartment}">
            <div class="card text-center ">
                <div class="card-header">
                    class: ${apartment.className}
                </div>

                <div class="card-body">
                   <%-- <h5 class="card-title">Apartment</h5>--%>

                    <div class="container d-flex">
                        <div class="container">
                            <c:if test="${not empty photos}">
                                <div class="d-flex justify-content-center" style="height: 350px;">
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

                        <div class="container" style="text-align: left; margin-left: 15px;">
                            <b>roomsCount:</b>  ${apartment.roomsCount} <br>
                            <b>class:</b> ${apartment.className} <br>
                            <b>adults capacity:</b> ${apartment.adultsCapacity} <br>
                            <b>childrenCapacity:</b> ${apartment.childrenCapacity} <br>
                            <b>Description:</b> <p class="card-text">${apartment.description}.</p>
                            <b>price:</b> ${apartment.price} <br><br>
                        </div>

                        <div class="container" style="text-align: left;">
                            <c:forEach items="${basicTags}" var="tag">
                                <span class="check">✓<span> ${tag.name}<br>
                            </c:forEach>
                        </div>
                    </div>

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
                                        <button type="submit" class="btn btn-success" data-bs-dismiss="modal">Confirm</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="action" value="booking">
                        <input type="hidden" name="apartmentId" value="${apartment.id}">
                        <input type="hidden" name="guests" value="${guests}">
                        <input type="hidden" name="startDate" value="${startDate}">
                        <input type="hidden" name="endDate" value="${endDate}">
                        <input type="hidden" name="isRequestOnly" value="false">

                        <c:if test="${not empty loggedUser}">
                            <button type="button" id="button-request" class="btn btn-primary"
                                data-bs-toggle="modal" data-bs-target="#staticBackdrop">Book it!</button>
                        </c:if>
                    </form>

                   <div class="accordion" id="accordionTags">
                       <div class="accordion-item">
                           <h2 class="accordion-header" id="headingOne">
                               <button class="accordion-button collapsed d-flex flex-column justify-content-center align-items-center" type="button"
                                       style="padding: 0;" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                   <span>Details</span>
                                   <i class="bi bi-chevron-down"></i>
                               </button>
                           </h2>
                           <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionTags">
                               <div class="accordion-body d-flex justify-content-center" style="text-align: left;">
                                   <c:forEach items="${allTags}" var="tag">
                                   <span class="check">✓<span> ${tag.name}<br>
                                </c:forEach>
                               </div>
                           </div>
                       </div>
                   </div>
                </div>

            </div>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
