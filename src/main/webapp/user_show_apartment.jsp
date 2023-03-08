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

      /*  .modal {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 50px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.8);
        }

        .modal-content {
            margin: auto;
            display: block;
            max-width: 80%;
            max-height: 80%;
        }

        .modal-content img {
            max-width: 100%;
            max-height: 80vh;
            margin: auto;
            display: block;
        }

        !* Стилі для фону поза зображенням *!
        .modal-background {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1;
        }*/
        /* Модальне вікно */
        .modal {
            display: none;
            position: fixed;
            z-index: 999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.8);
        }

        /* Вміст модального вікна */
        .modal-content {
            /*margin: auto;*/
            display: flex;
            /*flex-direction: column;*/
            justify-content: center;
            align-items: center;
            /*align-self: center;*/
            align-self: stretch;
            /*max-width: 800px;*/
            position: relative;
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.7);
            border-radius: 4px;
        }

        /*.modal-content {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.7);
            border-radius: 4px;
            margin: auto;
            box-sizing: border-box;

        }*/

        /* Зображення в модальному вікні */
        .modal-image {
            display: flex;
            justify-content: center;
            align-items: center;
            max-height: 80vh;
        }

        /*.modal-content {
            margin-top: 150px;
            max-width: 800px;
            background-color: #fff;
            opacity: 0.9;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }*/


        .modal-image img {
            max-width: 100%;
            max-height: 100%;
            object-fit: contain;
        }

        /* Хрестик закриття модального вікна */
        .close {
            position: absolute;
            top: 10px;
            right: 10px;
            font-size: 48px;
            font-weight: bold;
            cursor: pointer;
        }

        #myModal {
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5); /* кольоровий код фону з прозорістю 50% */
            z-index: 9999;
            display: none;
            overflow: auto;
        }
        /*img {
            max-width: 100%;
        }*/
    </style>
</head>
<body>
    <jsp:include page="/common/navbar.jsp" />

<%--    <div class="container mt-xl-4 mt-lg-3 mt-md-2 mt-sm-1">--%>
    <div class="container mt-5">
        <h5>Apartment details</h5> <br>
        <c:if test="${not empty apartment}">
            <div class="card <%--text-center--%> text-center">
                <div class="card-header">
                    Apartment ${apartment.className}
                </div>

                <div class="card-body">
                    <h5 class="card-title">Special title treatment</h5>

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

                                <div id="myModal" class="modal">
                                    <span class="close">&times;</span>
                                    <div class="modal-content">
                                        <div class="modal-image">
                                            <img id="modal-img" alt="">
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div> <%--container--%>

                        <div class="container">
                            <c:forEach items="${tags}" var="tag">
                            <span class="check">✓<span> ${tag.name}<br>
                            </c:forEach>
                        </div>
                    </div> <%--container d-flex--%>

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
                        <input type="hidden" name="action" value="book">
                        <input type="hidden" name="apartmentId" value="${apartment.id}">
                        <input type="hidden" name="guests" value="${guests}">
                        <input type="hidden" name="startDate" value="${startDate}">
                        <input type="hidden" name="endDate" value="${endDate}">
                        <button type="submit" class="btn btn-primary">Book it</button>
                    </form>
                </div> <%--card body--%>

            </div>  <%--card--%>
        </c:if> <%--apartment--%>
    </div> <%--container--%>

    <%--<script>
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
    </script>--%>

    <script>
        /*function openModal(img) {
            var modal = document.getElementById("myModal");
            var modalImg = document.getElementById("img01");

            // Встановлюємо зображення в модальне вікно
            modal.style.display = "block";
            modalImg.src = img.src;

            // Додаємо обробник події "click" до хрестика для закриття модального вікна
            var span = document.getElementsByClassName("close")[0];
            span.onclick = function() {
                modal.style.display = "none";
            }

            // Додаємо обробник події "click" до фону для закриття модального вікна
            var modalContent = document.getElementsByClassName("modal-content")[0];
            modalContent.onclick = function(event) {
                if (event.target == modalContent) {
                    modal.style.display = "none";
                }
            }
        }*/
       /* function openModal(img) {
            var modal = document.getElementById("myModal");
            var modalImg = document.getElementsByClassName("modal-image")[0];

            // Встановлюємо зображення в модальне вікно
            modal.style.display = "block";
            modalImg.style.backgroundImage = "url('" + img.src + "')";

            // Додаємо обробник події "click" до хрестика для закриття модального вікна
            var span = document.getElementsByClassName("close")[0];
            span.onclick = function() {
                modal.style.display = "none";
            }

            // Додаємо обробник події "click" до фону для закриття модального вікна
            var modalContent = document.getElementsByClassName("modal-content")[0];
            modalContent.onclick = function(event) {
                if (event.target == modalContent) {
                    modal.style.display = "none";
                }
            }
        }*/
        // Отримуємо всі елементи з класом "carousel-item"
        var carouselItems = document.querySelectorAll(".carousel-item");

        // Отримуємо модальне вікно
        var modal = document.getElementById("myModal");

        // Отримуємо зображення в модальному вікні
        var modalImg = document.getElementById("modal-img");

        // Отримуємо кнопку закриття модального вікна
        var closeBtn = document.getElementsByClassName("close")[0];

        // Перебираємо всі елементи з класом "carousel-item"
        carouselItems.forEach(function(item) {
            // Додаємо обробник події "click" до кожного елемента з класом "carousel-item"
            item.addEventListener("click", function() {
                // Встановлюємо зображення в модальне вікно
                modalImg.src = this.getElementsByTagName("img")[0].src;

                // Показуємо модальне вікно
                modal.style.display = "block";
            });
        });

        // Додаємо обробник події "click" до кнопки закриття модального вікна
        closeBtn.addEventListener("click", function() {
            // Ховаємо модальне вікно
            modal.style.display = "none";
        });

        function openModal(img) {
            var modal = document.getElementById("myModal");
            var modalImg = document.getElementsByClassName("modal-image")[0];

            // Встановлюємо зображення в модальне вікно
            modal.style.display = "block";
            modalImg.style.backgroundImage = "url('" + img.src + "')";

            // Додаємо обробник події "click" до хрестика для закриття модального вікна
            var span = document.getElementsByClassName("close")[0];
            span.onclick = function() {
                modal.style.display = "none";
            }

            // Додаємо обробник події "click" до фону для закриття модального вікна
            var modalContent = document.getElementsByClassName("modal-content")[0];
            modalContent.onclick = function(event) {
                if (event.target == modalContent) {
                    modal.style.display = "none";
                }
            }
        }
    </script>

<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
