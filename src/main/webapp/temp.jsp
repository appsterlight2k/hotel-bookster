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
<%--                            ${pageContext.request.contextPath}--%>

    <a href="login.jsp">SignIn</a> <br>
    <a href="registration.jsp">SignUp</a> <br>


    <form method="get" action="controller" id="form-logout">
        <input type="hidden" name="action" value="logout">
        <a href="controller">LogOut by Click!
            <input type="submit"/>
            <input type="hidden" name="action" value="logout">
        </a>
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
