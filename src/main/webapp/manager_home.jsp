<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


<title>JSP - Test page</title>
<style>
    .error { color: red; }
</style>

<html>
<head>
    <title>Manager Page</title>
</head>
<body>
    <jsp:include page="/common/navbar.jsp" />


    <div class="container mt-5">
        MANAGER PAGE <br>
        ${sessionScope.loggedUser.firstName}
        ${sessionScope.loggedUser.email} <br>

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
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
