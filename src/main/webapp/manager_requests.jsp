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
    </style>
</head>
<body>

    <jsp:include page="/common/header.jsp" />

<%--    <div class="container mt-5">--%>
    <div class="container-fluid mt-xxl-5" style="margin-bottom: 45px;">
        <div class="container-fluid" id="main-form">
            <form method="get" action="controller" id="form-search">
                <jsp:include page="/common/search_panel.jsp" />

                <c:if test="${not empty bookings}">
                    <c:if test="${isBookingRequestsTable}">
                        <jsp:include page="/parts/manager_booking_requests_table.jsp" />
                    </c:if>

                    <c:if test="${!isBookingRequestsTable}">
                        <jsp:include page="/parts/manager_requests_for_booking_table.jsp" />
                    </c:if>

                    <!-- Modal -->
                    <div class="modal fade" id="userModal" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-labelledby="userModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="userModalLabel">Customer info</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">

                                    <table class="table table-hover <%--table-primary--%>" id="modal-table">
                                        <thead>
                                        <tr>
                                            <td class="table-primary no-select">id</td>
                                            <td class="table-primary no-select">first name</td>
                                            <td class="table-primary no-select">last name</td>
                                            <td class="table-primary no-select">email</td>
                                            <td class="table-primary no-select">userPhoneNumber</td>
                                            <td class="table-primary no-select">userDescription</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td id="modal-user-id"></td>
                                                <td id="modal-user-firstname"></td>
                                                <td id="modal-user-lastname"></td>
                                                <td id="modal-user-email"></td>
                                                <td id="modal-user-phone"></td>
                                                <td id="modal-user-description"></td>
                                            </tr>
                                            <%--<input type="hidden" id="userId" name="userId" value="${userId}">--%>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-success" data-bs-dismiss="modal">Request</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
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
        function onSearchClick() {
            action.value = config.baseAction;
        }

        function SubmitSearch() {
            const search = document.getElementById('button-search');
            search.click();
        }
    </script>

    <script>
        const rows = document.querySelectorAll('table tbody tr');
        rows.forEach(row => {
            row.addEventListener('dblclick', e => {
                const userId = e.target.closest('tr').querySelector('#user-id').textContent;
                const firstName = e.target.closest('tr').querySelector('#user-firstname').textContent;
                const lastName = e.target.closest('tr').querySelector('#user-lastname').textContent;
                const email = e.target.closest('tr').querySelector('#user-email').textContent;
                const userPhoneNumber = e.target.closest('tr').querySelector('#user-phone').textContent;
                const userDescription = e.target.closest('tr').querySelector('#user-description').textContent;

                /*const modalUserId = document.getElementById("modal-user-id");
                const modalFirstName = document.getElementById("modal-user-firstname");
                const modalLastName = document.getElementById("modal-user-lastname");
                const modalEmail = document.getElementById("modal-user-email");
                const modalUserPhoneNumber = document.getElementById("modal-user-phone");
                const modalUserDescription = document.getElementById("modal-user-description");

                modalUserId.innerText = userId;
                modalFirstName.innerText = firstName;
                modalLastName.innerText = lastName;
                modalEmail.innerText = email;
                modalUserPhoneNumber.innerText = userPhoneNumber;
                modalUserDescription.innerText = userDescription;*/

                const table = document.querySelector('#modal-table');
                const row = table.rows[1];

                const id = row.querySelector('#modal-user-id');
                const userFirstname = row.querySelector('#modal-user-firstname');
                const userLastname = row.querySelector('#modal-user-lastname');
                const userEmail= row.querySelector('#modal-user-email');
                const userPhone = row.querySelector('#modal-user-phone');
                const userDescr = row.querySelector('#modal-user-description');

                id.textContent = userId;
                userFirstname.textContent = firstName;
                userLastname.textContent = lastName;
                userEmail.textContent = email;
                userPhone.textContent = userPhoneNumber;
                userDescr.textContent = userDescription;

                // const firstname = row.querySelector('#user-firstname');
                // firstname.textContent = 'good';

                console.log(name);
                showUserModal();
            });
        });

        function showUserModal() {
            var myModal = new bootstrap.Modal(document.getElementById('userModal'));

          /*  var modalTable = document.getElementById('modal-table');
            myModal._element.style.width = modalTable.offsetWidth + 'px';*/

            setUserInfo();
            myModal.show();
        }

        function setUserInfo() {
           /* const modalUserId = document.getElementById("modal-user-id");
            const modalFirstName = document.getElementById("modal-user-firstname");
            const modalLastName = document.getElementById("modal-user-lastname");
            const modalEmail = document.getElementById("modal-user-email");
            const modalUserPhoneNumber = document.getElementById("modal-user-phone");
            const modalUserDescription = document.getElementById("modal-user-description");

            modalUserId.innerText = userId.innerText;
            modalFirstName.innerText = firstName.innerText;
            modalLastName.innerText = lastName.innerText;
            modalEmail.innerText = email.innerText;
            modalUserPhoneNumber.innerText = userPhoneNumber.innerText;
            modalUserDescription.innerText = userDescription.innerText;

            console.log(userId.innerText + firstName.innerText + ' - ' + lastName.innerText + ' - ' + email.innerText);*/
        }
    </script>

    <script src="js/calendar.js"></script>
    <script src="js/multidate_control.js"></script>
    <script src="js/footer.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
