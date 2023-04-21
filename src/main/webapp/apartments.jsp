<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/material_blue.css">

    <title>Apartments</title>

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

    <link rel="stylesheet" type="text/css" href="css/main.css">

</head>
<body>
    <jsp:include page="/common/header.jsp" />

    <div class="container mt-xxl-5" style="margin-bottom: 45px;">
        <div class="container" id="main-form">

            <form method="get" action="controller" id="form-search" >
                <c:if test="${loggedUser.role == 'ROLE_USER'}">
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

                <jsp:include page="/common/search_panel.jsp" />

                <div class="container form-apartments" >
                    <c:if test="${not empty apartments}">
                        <c:if test="${loggedUser.role == 'ROLE_MANAGER'}">
                            <jsp:include page="/parts/manager_apartments_table.jsp" />
                        </c:if>

                        <c:if test="${empty loggedUser || loggedUser.role == 'ROLE_USER'}">
                            <jsp:include page="/parts/user_apartments_table.jsp" />
                        </c:if>
                    </c:if>
                </div>
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

    <script src="js/calendar.js"></script>

    <c:if test="${loggedUser.role == 'ROLE_USER'}">
        <script>
            function onRequestClick() {
                action.value = config.secondaryAction;

                let main_msg = getGuestsCount() + ' person(s) for ' +
                    (getStartDate() === getEndDate() ? getStartDate() :
                        'period of ' + getStartDate() + ' and ' + getEndDate());

                let modal = document.getElementById('modal-summary');

                if (config.apartmentsCount > 0) {
                    modal.innerText = ('You make a request for Apartment for ' + main_msg);
                } else {
                    modal.innerText = ('Warning! There are no free Apartments found for ' +
                        main_msg + '! Do you really want to make a request for Apartment?');
                }
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
    </c:if>

    <c:if test="${showGuestsControl}">
        <script src="js/guests_control.js"></script>
    </c:if>


    <c:if test="${loggedUser.role == 'ROLE_MANAGER'}">
        <script>
            window.addEventListener("DOMContentLoaded", (event) => {
                onChangeShowDescSwitch();
            });

            function hideDescriptionColumn() {
                var descriptionColumn = document.querySelectorAll(".description-column");
                descriptionColumn.forEach(function(el) {
                    el.style.display = "none";
                });
            }

            function showDescriptionColumn() {
                var descriptionColumn = document.querySelectorAll(".description-column");
                descriptionColumn.forEach(function(el) {
                    el.style.display = "table-cell";
                });
            }

            function onChangeShowDescSwitch() {
                const desc = document.getElementById("flexShowDesc");
                if (desc.checked) {
                    showDescriptionColumn();
                } else {
                    hideDescriptionColumn();
                }
            }
        </script>
    </c:if>

    <script src="js/footer.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
