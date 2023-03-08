<%--
  Created by IntelliJ IDEA.
  User: usr
  Date: 27.02.2023
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--onsubmit="return validatePassword();"--%>

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
</body>
</html>
