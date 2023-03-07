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


<%--    <div class="container mt-xl-4 mt-lg-3 mt-md-2 mt-sm-1">--%>
    <div class="container mt-5">
        <h5>Apartment details</h5> <br>
        ${apartment}
        <1><><><><><><><><><><><><><><><><><><><><><>
        <c:if test="${not empty apartment}">
            ${apartment.id}
            <2><><><><><><><><><><><><><><><><><><><><><>
           <%-- <c:forEach items="${apartments}" var="apartment">--%>

            <div class="card <%--text-center--%> text-left">
                <div class="card-header">
                    Apartment ${apartment.className}
                </div>

                <%--<div class="card-body">
                    <h5 class="card-title">Special title treatment</h5>
    &lt;%&ndash;            width="42" height="42" style="float:left"&ndash;%&gt;
    &lt;%&ndash;            style="height: 100px; margin: 0 10%";&ndash;%&gt;
                    <div class="container d-flex">
                        <div class="container">
                            <img src="${apartment.mainPhotoUrl}" alt="image" height="250" > <br>
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

                    &lt;%&ndash;<a href="/controller?action=" class="btn btn-primary" type="submit">Details...</a>
                    <br>&ndash;%&gt;
                    <br>

                    <c:if test="${not empty photos}">
                        <div class="d-flex justify-content-center">
                            <div id="carouselInterval" class="carousel slide d-flex justify-content-center" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <c:forEach items="${photos}" var="photo" varStatus="loop">
                                        <c:if test="${loop.index == 0}">
                                            <div class="carousel-item active" data-bs-interval= "7000">
                                                <img src="${photo}" class="d-block w-100 img-fluid" alt="image" height="300">
                                            </div>
                                        </c:if>

                                        <c:if test="${loop.index > 0}">
                                            <div class="carousel-item" data-bs-interval= "5000">
                                                <img src="${photo}" class="d-block w-100" alt="image" height="300">
                                            </div>
                                        </c:if>
                                    </c:forEach> <br>
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

                        &lt;%&ndash; modal window for fullscreen preview &ndash;%&gt;
                        <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <img src="" id="modalImage" class="img-fluid" alt="Image">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <br>

                    <form action="controller" method="POST">
                        <input type="hidden" name="action" value="book">
                        <input type="hidden" name="apartmentId" value="${apartment.id}">
                        <input type="hidden" name="guests" value="${guests}">
                        <input type="hidden" name="startDate" value="${startDate}">
                        <input type="hidden" name="endDate" value="${endDate}">
                        <button type="submit" class="btn btn-primary">Book</button>
    &lt;%&ndash;                ${pageContext.request.contextPath}&ndash;%&gt;
                    </form>
                </div>--%>

    <%--        <c:out value="${empty currentApartment.description ? '<br>' : currentApartment.description}" escapeXml="false" />--%>
            </div> //end card
            <%--</c:forEach>--%>
        </c:if>
    </div>

    <script>
        // Отримання зображень каруселі
        var carouselImages = document.querySelectorAll('#carouselInterval .carousel-item img');

        // Отримання модального вікна та зображення
        var modal = document.getElementById('myModal');
        var modalImage = document.getElementById('modalImage');

        // Додавання обробника подій до зображень каруселі
        carouselImages.forEach(function(image) {
            image.addEventListener('click', function() {
                // Встановлення зображення модального вікна на основі клікнутого зображення
                modalImage.src = this.src;
                modalImage.alt = this.alt;

                // Відкриття модального вікна
                modal.classList.add('show');
                modal.style.display = 'block';
            });
        });

        // Додавання обробника подій до модального вікна
        modal.addEventListener('click', function() {
            // Закриття модального вікна при кліку на підложку або хрестик
            modal.classList.remove('show');
            modal.style.display = 'none';
        });
    </script>

<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
