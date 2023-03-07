<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Register User</title>
    <style>
        .error { color: red; }
        .container {
            width: 500px; margin: auto; align-self: center;
        }
    </style>

</head>
<body>
    <jsp:include page="/common/navbar.jsp" />

    <div class="container mt-5">
        <form id="form-logout" action="controller" method="get">
            <input type="submit" class="button_active" value="Logout" style="align: right;">
            <input type="hidden" name="action" value="logout">
            <button type="submit" class="btn" form="form-logout" value="Submit">Submit</button>
        </form>


        <div class="container" id="div-registration" >
            <form class="login-form" method="post" action="controller">
                <input type="hidden" name="action" value="registration">

                <label class="form-label" for="firstname">First name:</label>
                <input class="form-control" type="text" name="firstname" id="firstname" required>

                <label class="form-label" for="lastname">Last name:</label>
                <input class="form-control" type="text" name="lastname" id="lastname" required>


                <label class="form-label" for="email">Email:</label>
                <input class="form-control" type="email" name="email" id="email" required>

                <label class="form-label" for="phone_number">Phone number:</label>
                <input class="form-control" type="text" name="phone_number" id="phone_number">
                <div style=" display: inline-flex; flex-wrap: wrap">
                    <div>
                        <label class="form-label" for="password"><b>Password:</b></label>
                        <input class="form-control" type="password" placeholder="Password" id="password" name="password" required>
                    </div>
                    <div>
                        <label for="confirm"><b>Confirm:</b></label>
                        <input class="form-control" type="password" placeholder="Confirm" id="confirm" name="confirm" required>
                    </div>
                </div>
                <br>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <p class="error">${sessionScope.error}</p>
    </div>



<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
