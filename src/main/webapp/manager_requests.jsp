<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/material_blue.css">
    <script src="https://kit.fontawesome.com/4678e6d780.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="css/main.css">

    <title>Requests and Bookings</title>

    <script>
        window.config = {
            startDate : "${startDate}",
            endDate: "${endDate}",
            apartmentsCount: "${totalCount}",
            pagesCount: "${pagesCount}",
            baseAction: "${baseAction}",
            secondaryAction: "${secondaryAction}"
        };
    </script>

    <style>
        .no-select {
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        #header-info {
            width: 280px;
        }
        #apartments-table input[type="checkbox"] {
            margin: 5px;
            /*vertical-align: middle;*/
        }
    </style>
</head>
<body>

    <jsp:include page="/common/header.jsp" />

    <div class="container-fluid mt-xxl-5" style="margin-bottom: 45px;">
        <div class="container-fluid" id="main-form">
            <form method="get" action="controller" id="form-search">
                <jsp:include page="/common/search_panel.jsp" />
                <c:if test="${isBookingRequestsTable}">
                    <input type="hidden" name="activeTab" value="4">
                </c:if>
                <c:if test="${!isBookingRequestsTable}">
                    <input type="hidden" name="activeTab" value="3">
                </c:if>

                <c:if test="${not empty bookings}">
                    <c:if test="${isBookingRequestsTable}">
                        <jsp:include page="/parts/manager_booking_requests_table.jsp" />
                    </c:if>

                    <c:if test="${!isBookingRequestsTable}">
                        <jsp:include page="/parts/manager_requests_for_booking_table.jsp" />
                    </c:if>

                    <!-- User Modal -->
                    <div class="modal fade" id="userModal" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-labelledby="userModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="userModalLabel">Customer info</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">

                                    <table class="table table-hover" id="modal-user-table">
                                        <thead>
                                        <tr>
                                            <td class="table-primary no-select">first name</td>
                                            <td class="table-primary no-select">last name</td>
                                            <td class="table-primary no-select">email</td>
                                            <td class="table-primary no-select">phone</td>
                                            <td class="table-primary no-select">description</td>
                                        </tr>
                                        </thead>
                                        <tbody></tbody>
                                    </table>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-success" data-bs-dismiss="modal" onclick="submitAjaxSearch()">Offer Apartments</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Apartments Modal -->
                    <div class="modal fade" id="apartmentsModal" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-labelledby="apartmentsModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header" style="padding: 10px;">
                                    <h5 class="modal-title" id="apartmentsModalLabel">Apartments List</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body" style="padding: 0 5px;">
                                    <p id="modal-total" style="margin: 5px 0 10px 10px;"></p>
                                    <div class="container form-apartments">
                                        <jsp:include page="/parts/manager_apartments_table.jsp" />
                                    </div>
                                </div>

                                <div class="modal-footer container-fluid d-flex justify-content-between" style="padding: 5px;">
                                    <div class="d-flex justify-content-start align-items-center" id="modal-pagination">
                                        <jsp:include page="/common/pagination_control_modal.jsp"/>
                                        <input type="hidden" id="modal-page" name="modal-page" value="1">
                                    </div>
                                    <div class="d-flex justify-content-end">
                                        <button type="button" id="offer-btn" class="btn btn-success" data-bs-dismiss="modal"
                                                onclick="onShowOfferMessage()" style="margin-right: 10px;" disabled>Write offer message</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Offer Message Modal Window-->
                    <div class="modal fade" id="offerModal" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-labelledby="offerModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header" style="padding: 10px;">
                                    <h5 class="modal-title" id="OfferModalLabel">Send Offer Message to Customer</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body" style="padding: 0 5px;">
                                    <div class="form-floating">
                                        <textarea class="form-control" id="message" name="comments"
                                                  placeholder="Leave an offer message" style="height: 100px"></textarea>
                                        <label for="message">Offer Message</label>
                                    </div>
                                </div>

                                <div class="modal-footer container-fluid d-flex justify-content-between" style="padding: 5px;">
                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-success" data-bs-dismiss="modal" onclick="onOfferApartments()" style="margin-right: 10px;">Offer</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <jsp:include page="/common/footer.jsp" />
            </form>

        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

    <script>
        let action = document.getElementById('search-action');

        function submitSearch() {
            action.value = config.baseAction;
            const form = document.getElementById('form-search');
            form.submit();
        }
    </script>

    <script>
        let response; //main response obj

        const rows = document.querySelectorAll('#table-req-for-booking tbody tr'); // #table-req-for-booking
        let selectedId;
        rows.forEach(row => {
            row.addEventListener('dblclick', e => {
                const bookingId = e.target.closest('tr').querySelector('#booking-id').textContent;
                const firstName = e.target.closest('tr').querySelector('#user-firstname').textContent;
                const lastName = e.target.closest('tr').querySelector('#user-lastname').textContent;
                const email = e.target.closest('tr').querySelector('#user-email').textContent;
                const userPhoneNumber = e.target.closest('tr').querySelector('#user-phone').textContent;
                const userDescription = e.target.closest('tr').querySelector('#user-description').textContent;

                selectedId = bookingId;

                const tbody = document.querySelector('#modal-user-table tbody'); //user table
                const row =
                    "<tr>" +
                    "<td>" + firstName + "</td>" +
                    "<td>" + lastName + "</td>" +
                    "<td>" + email + "</td>" +
                    "<td>" + userPhoneNumber + "</td>" +
                    "<td>" + userDescription + "</td>" +
                    "</tr>";
                tbody.innerHTML = row;

                showUserModal();
            });
        });

        function showUserModal() {
            let userModal = new bootstrap.Modal(document.getElementById('userModal'));
            userModal.show();
        }


        function submitAjaxSearch() {
            //bookings from server
            doAjaxRequest(selectedId, function(response) {
                const checkApartments = setInterval(function() {
                    if (response) {
                        fillApartmentsTable(response);
                        clearInterval(checkApartments);
                        setPaginationParams(response);
                    }
                }, 100);
            });

            showApartmentsModal();
        }

        function doAjaxRequest(id, callback) {
            // Create object of request
            let xhttp = new XMLHttpRequest();

            //Set request method and URL of servlet
            xhttp.open("POST", "controller?action=apartments", true);

            //set type of request content
            // xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); //for gerParameter
            xhttp.setRequestHeader("Content-type", "application/json");

            //get response from server
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    // Process response from server
                    response = JSON.parse(this.responseText);
                    callback(response);
                }
            };

            let page = document.getElementById("modal-page").value;
            let { value: pageSize } = document.querySelector("#modal-pagination select.form-select").selectedOptions[0];
            //send data to server
            let data = {
                bookingId : selectedId,
                page : page,
                pageSize: pageSize
            }

            let dataJSON = JSON.stringify(data);
            xhttp.send(dataJSON);
        }

        let checkboxStates = {};
        let ids = [];
        let apartments = [];

        function fillApartmentsTable(response) {
            let tableBody = document.querySelector("#apartments-table tbody");
            tableBody.innerHTML = "";

            const tdElement = document.querySelector('#apartments-table thead tr td');
            tdElement.className = 'table-primary';

            apartments = response.apartments || [];
            const page = response.page || 1;
            const pageSize = response.modalPageSize || 15;

            const total = document.getElementById('modal-total');
            total.innerText = 'Total Apartments: ' + response.totalCount;

            let ind = 1;
            if (apartments !== undefined && apartments !== null) {
                apartments.forEach(function (apartment) {
                    const row = "<tr onclick='toggleCheckbox(this)'>" +
                        "<td><input type='checkbox' name='selected[]' value='" + (ind) +
                        "' onclick='event.stopPropagation(); toggleCheckbox(this.parentNode.parentNode)'></td>" +
                        "<td>" + (ind++ + ((page - 1) * pageSize)) + ".</td>" +
                        "<td class='d-none' data-name='id'>" + apartment.id + "</td>" +
                        "<td>" + apartment.className + "</td>" +
                        "<td>" + apartment.roomsCount + "</td>" +
                        "<td>" + apartment.adultsCapacity + "</td>" +
                        "<td>" + apartment.price + "</td>" +
                        "<td>" + apartment.status + "</td>" +
                        "<td class='description-column'>" + apartment.description + "</td>" +
                        "</tr>";

                    tableBody.innerHTML += row;
                });
                restoreCheckboxStates();
            }
        }

        function toggleCheckbox(row) {
            let checkbox = row.querySelector('input[type="checkbox"]');
            if (checkbox && event.target.tagName !== 'INPUT') {
                checkbox.checked = !checkbox.checked;
            }

            // Save checkbox state
            saveCheckboxState(checkbox);
        }

        function saveCheckboxState(checkbox) {
            const page = document.querySelector('#modal-page').value;
            const { value: pageSize } = document.querySelector("#modal-pagination select.form-select").selectedOptions[0];
            const rowIndex = checkbox.closest("tr").rowIndex;
            const checked = checkbox.checked;

            // Calculate index based on current page and page size
            const index = (page - 1) * pageSize + rowIndex - 1;

            // Save checkbox state in the checkboxStates object
            checkboxStates[index] = checked;

            // Save id of selected row in the ids array
            const apartmentId = parseInt(checkbox.closest("tr").querySelector("td[data-name='id']").textContent);

            if (checked && !ids.includes(apartmentId)) {
                ids.push(apartmentId);
            } else if (!checked && ids.includes(apartmentId)) {
                // ids.splice(ids.indexOf(apartmentId), 1);
                ids = ids.filter(id => id !== apartmentId);
            }

            const offer = document.getElementById("offer-btn");
            if (ids.length > 0) {
                offer.removeAttribute('disabled');
            } else {
                offer.setAttribute('disabled', true);
            }
        }

        function restoreCheckboxStates() {
            const page = document.querySelector('#modal-page').value;
            const { value: pageSize } = document.querySelector("#modal-pagination select.form-select").selectedOptions[0];
            const checkboxes = document.querySelectorAll("#apartments-table tbody input[type='checkbox']");

            checkboxes.forEach(function (checkbox, rowIndex) {
                const index = (page - 1) * pageSize + rowIndex;

                if (checkboxStates.hasOwnProperty(index)) {
                    checkbox.checked = checkboxStates[index];
                }
            });
        }

        let apartmentsModal;

        function showApartmentsModal() {
            if (apartmentsModal === undefined) {
                let modal = document.getElementById('apartmentsModal');
                apartmentsModal = new bootstrap.Modal(modal);
                modal.addEventListener('hidden.bs.modal', function () {
                    ids = [];
                    checkboxStates = {};
                });
            }
            apartmentsModal.show();
        }

        let OfferMessageModal;
        let offerList; //the final list with apartments
        function onShowOfferMessage() {
            offerList = ids;
            if (OfferMessageModal === undefined) {
                OfferMessageModal = new bootstrap.Modal(document.getElementById('offerModal'));
            }
            OfferMessageModal.show();
        }

        function getSelectedApartments() {
            return offerList;
        }

        function onOfferApartments() {
            const apartmentsId = getSelectedApartments();
            const message = document.getElementById("message").value;

            let xhttp = new XMLHttpRequest();
            xhttp.open("POST", "controller?action=manager-offer-for-request&activeTab=3", true);
            xhttp.setRequestHeader("Content-type", "application/json");

            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    submitSearch();
                }
            };

            let data = {
                apartmentsId: apartmentsId,
                bookingId : selectedId,
                message : message,
            };

            let dataJSON = JSON.stringify(data);
            xhttp.send(dataJSON);
            offerList = [];
        }

        function setModalPageSize(size) {
            const pageSizeSelect = document.querySelector("#modal-pagination select.form-select");

            for (let i = 0; i < pageSizeSelect.options.length; i++) {
                const option = pageSizeSelect.options[i];
                if (option.innerText === size) {
                    pageSizeSelect.selectedIndex = i;
                    break;
                }
            }
        }

        //set parameters of pagination of Apartments modal:
        function setPaginationParams(response) {
            setModalPageSize(response.modalPageSize);
            generatePageButtons(response);
        }
    </script>

    <script src="js/calendar.js"></script>
    <script src="js/multidate_control.js"></script>
    <script src="js/header.js"></script>
    <script src="js/footer.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
