<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--onsubmit="return validatePassword();"--%>
<%--<input type="hidden" &lt;%&ndash;form="form-details"&ndash;%&gt; name="action" value="get-apartment">
                                        <input type="hidden" &lt;%&ndash;form="form-details"&ndash;%&gt; name="apartmentId" value="${currentApartment.id}">
                                        <input type="hidden" &lt;%&ndash;form="form-details"&ndash;%&gt; id="startDateForDetails" name="startDateForDetails" value="${startDate}">
                                        <input type="hidden" &lt;%&ndash;form="form-details"&ndash;%&gt; id="endDateForDetails" name="endDateForDetails" value="${endDate}">
                                        <input type="hidden" &lt;%&ndash;form="form-details"&ndash;%&gt; id="guestsForDetails" name="guests" value="${guests}">--%>
<%--<div style="display: flex; flex-flow: column wrap; justify-content: flex-end;">
    <button type="submit" id="button-get-apartment" class="btn btn-primary" form="form-details">Details...</button>
</div>--%>
<%--                            ${pageContext.request.contextPath}--%>
    ${pageContext.request.contextPath}
    <a href="login.jsp">SignIn</a> <br>
    <a href="registration.jsp">SignUp</a> <br>
<%-- <c:out value="${empty currentApartment.description ? '<br>' : currentApartment.description}" escapeXml="false" />--%>
<%--                    <a href="/controller?action=get-apartment" class="btn btn-primary" type="submit">Details...</a>--%>
<%--    <div class="container mt-xl-4 mt-lg-3 mt-md-2 mt-sm-1">--%>

    <form method="get" action="controller" id="form-logout">
        <input type="hidden" name="action" value="logout">
        <a href="controller">LogOut by Click!
            <input type="submit"/>
            <input type="hidden" name="action" value="logout">
        </a>
    </form>

<form id="form-logout" action="controller" method="get">
    <input type="submit" class="button_active" value="Logout" style="align: right;">
    <input type="hidden" name="action" value="logout">
    <button type="submit" class="btn" form="form-logout" value="Submit">Submit</button>
</form>

    <%--<a href="controller" type="submit" class="button" id="logout">Logout</a>
    <input type="button" class="button_active" onclick="location.href='logout';" />
     <input type="hidden" name="action" value="logout">--%>
    <%-- <c:out value="${empty currentTariff.description ? '<br>' : currentTariff.description}" escapeXml="false" />--%>
    <form method="get" action="controller" id="form-logout">
        <input type="hidden" name="action" value="logout">
    </form>


    <button type="submit" form="form-logout" value="Submit"> Logout </button>

    <div style="align: auto;">
        <form id="form-home" method="POST" action="controller">
            <input type="hidden" name="action" value="home">

            <label class="form-label" for="email">Email:</label>
            <input class="form-control" type="email" name="email" id="email" required>

            <button type="submit" class="btn" form="form-home" value="Submit">Submit</button>
        </form>
    </div>

    <button type="submit" > Submit </button><br>

    <a href="test">Test Servlet</a> <br>

   <%-- ////// from manager home--%>
    <br>
    <a href="login.jsp">SignIn</a> <br>
    <a href="registration.jsp">SignUp</a> <br>

    <a href="controller">LogOut by Click!
        <input type="submit"/>
        <input type="hidden" name="action" value="logout">
    </a>

    //logout btn
    <form method="GET" action="logout"  id="form-logout">
        <input type="hidden" name="action" value="logout">
    </form>
    <button type="submit" form="form-logout" value="Submit"> Submit </button><br>


    <form method="POST" action="choose-files"  id="form1">
        <label for="filename">Choose photos:</label>
        <input type="text" id="filename" name="file-dialog" value="c:\photo.jpg"><br><br>

    </form>

    <a href="test">Test Servlet</a> <br>
    <a href="choose-files">Choose photos</a><br>



<script>
    /* window.addEventListener('load', function() {
         const menuField = document.getElementById('menu');
         const menuId = menuField.value;
         const menuItems = document.querySelectorAll('.nav-item');

         menuItems.forEach((menuItem) => {
             const href = menuItem.querySelector('a').getAttribute('href');
             const id = menuItem.getAttribute('id');
             const newHref = href + `&menu=${id}`;

                menuItem.querySelector('a').setAttribute('href', newHref);

                if (id === menuId) {
                    menuItem.querySelector('a').classList.add('active');
                }
            });
        });

        const menuItems = document.querySelectorAll('.nav-item');

        menuItems.forEach(menuItem => {
            menuItem.addEventListener('mousedown', () => {
                const id = menuItem.parentElement.getAttribute('id');
                const menuField = document.getElementById('menu');
                menuField.value = menuItem.id;

                const href = menuItem.getAttribute('href');
                menuItem.setAttribute('href', href + `&menu=${id}`);

                menuItems.forEach(item => {
                    item.querySelector('a').classList.remove('active');
                });

                menuItem.querySelector('a').classList.add('active');
            });
        });*/


    let calendar = flatpickr("#datepicker", {
        dateFormat: "Y-m-d",
        locale: "en",
        theme: "material_blue",
        /*confirmIcon: "<i class='fa fa-check'></i>",
        confirmText: "OK ",*/
        disableMobile: true,
        /*maxDate: new Date().fp_incr(30)*/
        onChange: function(selectedDates, dateStr, instance) {
            let isMultidate = true;
        }
    });

</script>
</body>
</html>
