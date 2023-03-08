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

    <title>Apartments</title>

    <script>
        window.config = {startDate : "${startDate}", endDate: "${endDate}"};
    </script>

    <style>
        .error { color: red; }
    </style>
</head>
<body>
    <jsp:include page="/common/navbar.jsp" />

    <div class="container mt-xxl-5">
        <h5>${sessionScope.loggedUser.firstName}, Please choose and Apartment</h5> <br>

        <div class="container" id="main-form">
            <form method="get" action="controller" id="form-search" >
                <div class="container">
                    <ul class="nav justify-content-center">
                        <li class="nav-item">
                            <div class="container-sm" style="width: 250px;">
                                <input type="text" id="myFlatpickr" placeholder="Select Date Range" data-input>
                            </div>
                        </li>
                        <li class="nav-item">
                            <div class="container-sm" style="width: 180px;">
                                <label for="range" class="form-label" id="rangeValue">Guests number: ${guests}</label>
                                <input type="range" class="form-range" min="1" max="10" id="range" name="range" value="${guests}">
                            </div>
                        </li>
                        <li class="nav-item">
                            <input type="hidden" id="search-action" name="action" value="apartments">
                            <input type="hidden" id="startDate" name="startDate" value="${startDate}">
                            <input type="hidden" id="endDate" name="endDate" value="${endDate}">
                            <button type="submit" id="button-search" class="btn btn-primary" onclick="onSearchClick()">Search</button>
                        </li>
                        <li class="nav-item">
                            <c:if test="${not empty apartments}">
                                <p>   Apartments found: ${apartments.size()}</p>
                            </c:if>
                        </li>
                        <li>
                            <!-- Button trigger modal -->
                            <button type="button" id="button-request" class="btn btn-primary"
                                    data-bs-toggle="modal" data-bs-target="#staticBackdrop">Book Request</button>
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
                                            <h6 id="modal-summary">You make request for Apartment for ${guests} person(s) between ${startDate} and ${endDate} </h6>

                                            <form>
                                                <div class="mb-3">
                                                    <label for="comments" class="col-form-label">Comments:</label>
                                                    <textarea class="form-control" id="comments" name="comments"></textarea>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                            <button type="submit" class="btn btn-primary" data-bs-dismiss="modal" onclick="onRequestClick()">Request</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </c:if>
                </div>
            </form>
        </div> <%-- search-form --%>


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

                            <form action="controller" method="get" id="form-apartment">
                                <input type="hidden" id="apartment-action" name="action" value="get-apartment">
                                <input type="hidden" name="apartmentId" value="${currentApartment.id}">
                                <input type="hidden" id="startDateMain" name="startDateMain" value="${startDate}">
                                <input type="hidden" id="endDateMain" name="endDateMain" value="${endDate}">
                                <input type="hidden" id="guests" name="guests" value="${guests}">
                               <button type="submit" id="button-get-apartment" class="btn btn-primary">Details...</button>
                            </form>
                        </div> <%-- card-body --%>
                    </div> <%--card --%>
                </c:forEach>
            </c:if>
        </div> <%-- form-search --%>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

    <script>
        var today = parseDateInputValue(new Date());
        console.log("today: " + today);

        flatpickr("#myFlatpickr", {
            mode: "range",
            dateFormat: "Y-m-d",
            defaultDate: [config.startDate, config.endDate],
            locale: "en",
            theme: "airbnb",
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

                    var startDateMainInput = document.getElementById("startDateMain");
                    var endDateMainInput = document.getElementById("endDateMain");

                    startDateMainInput.value = parseDateInputValue(startDate);
                    endDateMainInput.value = parseDateInputValue(endDate);

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
        function onRequestClick() {
            var action = document.getElementById('search-action');
            action.value = 'booking';

            var modal = document.getElementById('modal-summary');
            modal.innerHTML = "sdfsdfsdfs";
        }
        function onSearchClick() {
            var action = document.getElementById('search-action');
            action.value = 'apartments';

        }
        function onShowApartmentClick() {
            var action = document.getElementById('search-action');
            action.value = 'get-apartment';

        }

    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
