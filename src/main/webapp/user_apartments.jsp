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

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/material_blue.css">

    <title>Apartments</title>

    <script>
        window.config = {startDate : "${startDate}", endDate: "${endDate}"};
    </script>

    <style>
        .error { color: red; }
        #myFlatpickr {
            width: 230px;
            text-align: center;
            box-shadow: 0 0 10px gray;
        }

        .container-centred {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        /*#container-flatpickr {
            display: flex;
            align-items: center;
            justify-content: center;
        }*/

    </style>
</head>
<body>
    <jsp:include page="/common/navbar.jsp" />

    <div class="container mt-xxl-5">
        <h5>${sessionScope.loggedUser.firstName}, Please choose an Apartment</h5> <br>

        <div class="container" id="main-form">
            <form method="get" action="controller" id="form-search" >
                <div class="container" id="search-panel">
                    <ul class="nav justify-content-center">
                        <li class="nav-item">
                            <div class="container-sm centred container-centred" style="width: 250px;">
                                <input type="text" id="myFlatpickr" placeholder="Select Date Range" data-input>
                            </div>
                        </li>
                        <li class="nav-item">
                            <div class="container-sm" style="width: 160px; height: 100%">
                                <label for="range" class="form-label" id="rangeValue" style="margin-bottom: 0px;">Guests number: ${guests}</label>
                                <input type="range" class="form-range" min="1" max="10" id="range" name="range" value="${guests}">
                            </div>
                        </li>
                        <li>
                            <div class="container container-centred">
                                <select class="form-select" name="apartmentClass" aria-label="Choose Apartment Class:">
                                    <option selected>All Classes</option>
                                    <c:if test="${not empty apartmentClasses}">
                                        <c:forEach items="${apartmentClasses}" var="apartClass">
                                            <option value="${apartClass.id}"> ${apartClass.name} </option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>

                        </li>
                        <li class="nav-item">
                            <input type="hidden" id="search-action" name="action" value="apartments">
                            <input type="hidden" id="startDate" name="startDate" value="${startDate}">
                            <input type="hidden" id="endDate" name="endDate" value="${endDate}">
                            <div class="container-centred">
                                <button type="submit" id="button-search" class="btn btn-light" style="color: #569ff7; border-color: #0d6efd; font-weight: bold;"
                                        onclick="onSearchClick()">Search</button>
                            </div>

                        </li>
                        <li class="nav-item">
                            <div class="container-centred">
                                <c:if test="${not empty apartments}">
                                    <div style="width: 195px; margin: 0 auto;">
                                        <p style="margin: 0 20px;">Apartments found: ${apartments.size()}</p>
                                    </div>
                                </c:if>
                            </div>
                        </li>
                        <li>
                            <div class="container-centred">
                                <button type="button" id="button-request" class="btn btn-primary"
                                        data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="onRequestClick()">Book Request</button>
                            </div>
                        </li>
                    </ul>

                    <c:if test="${not empty loggedUser}">
                        <!-- Modal -->
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel">Application</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h6><p id="modal-summary"></p></h6>
                                        <div class="mb-3">
                                            <label for="comments" class="col-form-label">Comments:</label>
                                            <textarea class="form-control" id="comments" name="comments"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-success" data-bs-dismiss="modal" <%--onclick="onRequestClick()"--%>>Request</button>
                                    </div>
                                    <input type="hidden" id="is-request-only" name="isRequestOnly" value="true">
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>

                <div class="container form-apartments" >
                    <c:if test="${not empty apartments}">
                        <c:forEach items="${apartments}" var="currentApartment">

                            <div class="card text-center" style="padding-bottom: 20px;">
                                <div class="card-header">
                                        ${currentApartment.className}
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">Special title treatment</h5>
                                    <img src="${currentApartment.mainPhotoUrl}" alt="image" height="200" ><br>
                                    <b>roomsCount:</b>  ${currentApartment.roomsCount} <br>
                                    <b>adults capacity:</b> ${currentApartment.adultsCapacity} <br>
                                    <b>childrenCapacity:</b> ${currentApartment.childrenCapacity} <br>
                                    <b>price:</b> ${currentApartment.price} <br><br>
                                    <b>Description:</b> <p class="card-text">${currentApartment.description}.</p>

                                    <input type="hidden" form="form-search" name="apartmentId" value="${currentApartment.id}">
                                    <button type="submit" id="button-get-apartment" class="btn btn-primary" form="form-search"
                                            onclick="onShowApartmentClick()">Details...</button>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

    <script>
        var today = parseDateInputValue(new Date());

        flatpickr("#myFlatpickr", {
            mode: "range",
            dateFormat: "Y-m-d",
            defaultDate: [config.startDate, config.endDate],
            locale: "en",
            theme: "material_blue",
            minDate:  today,
            disableMobile: true,
            /*maxDate: new Date().fp_incr(30)*/
            onChange: function(selectedDates, dateStr, instance) {
                if (selectedDates.length === 2) {
                    var startDate = selectedDates[0];
                    var endDate = selectedDates[1];

                    var startDateInput = document.getElementById("startDate");
                    var endDateInput = document.getElementById("endDate");

                    startDateInput.value = parseDateInputValue(startDate);
                    endDateInput.value = parseDateInputValue(endDate);
                }
            }
        });

        function parseDateInputValue(date) {
            return date.getFullYear() + '-' +
                (date.getMonth() + 1).toString().padStart(2, '0') +
                '-' + date.getDate().toString().padStart(2, '0');
        }
    </script>

    <script>
        var range = document.getElementById('range');
        var rangeValue = document.getElementById("rangeValue");

        rangeValue.innerHTML = "Guests number: " + range.value;

        range.addEventListener("input", function() {
            range.setAttribute('value', range.value);
            rangeValue.innerHTML = "Guests number: " + range.value;
            var guests = document.getElementById('guests');
            guests.value = range.value;
        });

        function getRange() {
            return range.value;
        }
    </script>

    <script>
        var action = document.getElementById('search-action');

        function onRequestClick() {
            action.value = 'booking';
            var modal = document.getElementById('modal-summary');
            modal.innerText = 'You make a request for Apartment for ' + getGuestsCount() + ' person(s) for ' +
                (getStartDate() === getEndDate() ? getStartDate() : 'period of ' + getStartDate() + ' and ' + getEndDate());
        }

        function onSearchClick() {
            action.value = 'apartments';
        }

        function onShowApartmentClick() {
            action.value = 'get-apartment';
        }

        function getGuestsCount() {
            return document.getElementById('range').value;
        }

        function getStartDate() {
            return document.getElementById("startDate").value;
        }
        function getEndDate() {
            return document.getElementById("endDate").value;
        }

    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
