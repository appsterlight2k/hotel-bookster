<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <title>JSP - Test page</title>
    <style>
        .error { color: red; }
    </style>

</head>
<body>
 <a href="" id="username" >${sessionScope.loggedUser.firstName}</a>
<br/>
 ${sessionScope.loggedUser.firstName}
 ${sessionScope.loggedUser.email}

    <br>
    <a href="login.jsp">SignIn</a> <br>
    <a href="register.jsp">SignUp</a> <br>
    <%--<a href="controller" type="submit" class="button" id="logout">Logout</a>
    <input type="button" class="button_active" onclick="location.href='logout';" />
     <input type="hidden" name="action" value="logout">--%>

    <form method="get" action="controller" id="form_logout">
        <input type="hidden" name="action" value="logout">
    </form>


    <button type="submit" form="form_logout" value="Submit"> Logout </button>

    <div style="align: auto;">
        <form method="get" action="controller">
            <input type="hidden" name="action" value="home">

            <label class="form-label" for="email">Email:</label>
            <input class="form-control" type="email" name="email" id="email" required>

            <button type="submit" class="btn">Submit</button>
        </form>
    </div>

    <form method="POST" action="choose-files"  id="form1">
        <label for="filename">Choose photos:</label>
        <input type="text" id="filename" name="file-dialog" value="c:\photo.jpg"><br><br>

    </form>

    <a href="test">Test Servlet</a> <br>

    <button type="submit" form="form1" value="Submit"> Submit </button><br>


    <a href="choose-files">Choose photos</a><br>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>