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
        window.config = {startDate : "${startDate}", endDate: "${endDate}", apartmentsCount: "${apartments.size()}"};
    </script>

    <style>
        body {
            padding-top: 92px;
        }
        .error { color: red; }

        #myFlatpickr {
            width: 230px;
            text-align: center;
            box-shadow: 0 0 10px gray;
            border-width: 1px;
        }

        .container-centred {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        #search-panel {
            margin-top: 38px;
            background-color: white;
            border: 1px solid lightgray;
            border-radius: 0 0 17px 17px;
        }

        .card-container {
            margin-bottom: 10px;
            border-radius: 22px;
        }

        .page-bar {
            height: 45px;
            padding: 0;
        }

        .inner-page-bar {
            /*height: 100%;*/
            display: flex;
            justify-content: flex-end;
            height: 45px;
            border-top: 1px solid lightgray;
            width: auto;
        }

        #pagination-bar {
            margin-top: 3px;
        }
    </style>
</head>
<body>
    <jsp:include page="/common/navbar.jsp" />

    <div class="container mt-xxl-5">
        <div class="container" id="main-form">
            <form method="get" action="controller" id="form-search" >
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
                                    <button type="submit" class="btn btn-success" data-bs-dismiss="modal">Request</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                </div>
                                <input type="hidden" id="is-request-only" name="isRequestOnly" value="true">
                            </div>
                        </div>
                    </div>
                </c:if>

                <div class="container-fluid fixed-top" id="search-panel">
                    <ul class="nav justify-content-center">
                        <li class="nav-item">
                            <div class="container-sm centred container-centred" style="width: 250px;">
                                <input type="text" id="myFlatpickr" placeholder="Select Date Range" data-input>
                            </div>
                        </li>
                        <li class="nav-item">
                            <div class="container-sm" style="width: 160px; height: 100%">
                                <label for="range" class="form-label" id="rangeValue" style="margin-bottom: 0px;">Guests number: ${guests}</label>
                                <input type="range" class="form-range" min="1" max="10" id="range" name="guests" value="${guests}">
                            </div>
                        </li>
                        <li>
                            <div class="container container-centred">
                                <select class="form-select" name="apartmentClass" onchange="SubmitSearch()"
                                                                                    aria-label="Choose Apartment Class:">
                                    <c:if test="${not empty apartmentClasses}">
                                        <c:choose>
                                            <c:when test="${chosenClass == 0}">
                                                <option value="0" selected>All Classes</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="0">All Classes</option>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:forEach items="${apartmentClasses}" var="apartClass">
                                            <c:choose>
                                                <c:when test="${chosenClass == apartClass.id}">
                                                    <option value="${apartClass.id}" selected> ${apartClass.name} </option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${apartClass.id}"> ${apartClass.name} </option>
                                                </c:otherwise>
                                            </c:choose>
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
                                    <div style="width: 195px; margin: 0 auto;">
                                        <p style="margin: 0 20px;">Apartments found: ${apartments.size()}</p>
                                    </div>
                            </div>
                        </li>
                        <li>
                            <div class="container-centred">
                                <button type="button" id="button-request" class="btn btn-primary"
                                        data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="onRequestClick()">Book Request</button>
                            </div>
                        </li>
                    </ul>
                </div>

                <div class="container form-apartments" >
                    <c:if test="${not empty apartments}">
                        <c:forEach items="${apartments}" var="currentApartment">
                            <form method="get" action="controller" id="form-details">
                                <div class="card text-center card-container">
                                    <div class="card-header" style="border-radius: 22px 22px 0 0; background-color: #569ff752;">
                                            ${currentApartment.className}
                                    </div>
                                    <div class="card-body">
                                        <img src="${currentApartment.mainPhotoUrl}" alt="image" height="200" ><br>
                                        <b>roomsCount:</b>  ${currentApartment.roomsCount} <br>
                                        <b>adults capacity:</b> ${currentApartment.adultsCapacity} <br>
                                        <b>childrenCapacity:</b> ${currentApartment.childrenCapacity} <br>
                                        <b>price:</b> ${currentApartment.price} <br><br>
                                        <b>Description:</b> <p class="card-text">${currentApartment.description}.</p>

                                        <input type="hidden" form="form-details" name="action" value="get-apartment">
                                        <input type="hidden" form="form-details" name="apartmentId" value="${currentApartment.id}">
                                        <input type="hidden" form="form-details" id="startDateForDetails" name="startDateForDetails" value="${startDate}">
                                        <input type="hidden" form="form-details" id="endDateForDetails" name="endDateForDetails" value="${endDate}">
                                        <input type="hidden" form="form-details" id="guestsForDetails" name="guests" value="${guests}">

                                        <button type="submit" id="button-get-apartment" class="btn btn-primary" form="form-details">Details...</button>
                                    </div>
                                </div>

                            </form>

                        </c:forEach>
                    </c:if>
                </div>

                <nav class="navbar fixed-bottom navbar-light bg-light page-bar">
                    <div class="container inner-page-bar">
                        <nav aria-label="Choose pages" id="pagination-bar" >
                            <ul class="pagination <%--ml-auto--%>">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>

                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item active" aria-current="page">
                                    <a class="page-link" href="#">2</a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>

                    </div>
                </nav>
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

                    var startDateForDetailsInput = document.getElementById("startDateForDetails");
                    var endDateForDetailsInput = document.getElementById("endDateForDetails");
                    startDateForDetailsInput.value = parseDateInputValue(startDate);
                    endDateForDetailsInput.value = parseDateInputValue(endDate);

                    SubmitSearch();
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
            /*var guests = document.getElementById('guests');
            guests.value = range.value;*/

            var guestsForDetails = document.getElementById('guestsForDetails');
            guestsForDetails.value = range.value;
        });

        range.addEventListener("change", function() {
            SubmitSearch();
        });

        function getRange() {
            return range.value;
        }
    </script>

    <script>
        var action = document.getElementById('search-action');

        function onRequestClick() {
            action.value = 'booking';

            var main_msg = getGuestsCount() + ' person(s) for ' +
                (getStartDate() === getEndDate() ? getStartDate() :
                    'period of ' + getStartDate() + ' and ' + getEndDate());

            var modal = document.getElementById('modal-summary');

            if (config.apartmentsCount > 0) {
                modal.innerText = ('You make a request for Apartment for ' + main_msg);
            } else {
                modal.innerText = ('Warning! There are no free Apartments found for ' +
                    main_msg + '! Do you really want to make a request for Apartment?');
            }
        }

        function SubmitSearch() {
            const search = document.getElementById('button-search');
            search.click();
        }

        function onSearchClick() {
            action.value = 'apartments';
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
