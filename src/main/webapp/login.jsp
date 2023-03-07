<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<%--    DELETE--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">


    <title>Login</title>

    <style>
        .error { color: red; }
        .div-login {
            width: 500px; margin: auto; align-self: center;
        }
    </style>
    <link href="/css/signin.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/common/navbar.jsp" />

    <a href="index.jsp">home</a> <br>
    <input type="hidden" name="action" value="logout">

    <div class="container-sm d-flex justify-content-center align-items-center mt-5">
        <form class="login-form mb-3" method="post" action="controller">
            <input type="hidden" name="action" value="login">

            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required>
                <label for="email">Email:</label>
            </div>

            <%--<label for="pass"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" id="pass" name="pass" required>--%>

            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="pass" placeholder="Password" name="pass" required>
                <label for="pass">Password</label>
            </div>

            <button type="submit" class="btn btn-primary">Login</button>

            <%--<div class="container" style="background-color:#f1f1f1">
                <button type="button" class="cancelbtn">Cancel</button>
                <span class="pass">Forgot <a href="#">password?</a></span>
            </div>--%>

        </form>
        <p class="error">${sessionScope.error}</p>
    </div>

    <div class="container">
        <h1>Bootstrap 5 Datepicker Example</h1>
        <input type="text" id="myDatepicker" class="form-control" placeholder="Select Date">
    </div>

    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <script>
        flatpickr("#myDatepicker", {
            dateFormat: "Y-m-d",
            disableMobile: true
        });
    </script>

<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
