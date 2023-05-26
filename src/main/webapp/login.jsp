<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css">

    <title>Login</title>

    <style>
        body {
            padding: 0;
        }
        .form-floating {
            width: 400px;
        }
    </style>
</head>
<body>

    <jsp:include page="/common/header.jsp" />

    <div class="container d-flex justify-content-center align-items-center vh-100 text-center">
        <form class="login-form mb-3" method="post" action="controller">
            <input type="hidden" name="action" value="login">
            <div class="form-floating flex-grow-1 mb-3">
                <h4>Enter you email and password</h4>
            </div>

            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required autofocus>
                <label for="email">Email:</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="pass" placeholder="Password" name="pass" required>
                <label for="pass">Password</label>
            </div>

            <button type="submit" class="btn btn-primary">SignIn</button>
        </form>
        <p class="error">${error}</p>
    </div>

    <script>
        function validatePassword() {
            const password = document.getElementById("password").value;
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
            if (!passwordRegex.test(password)) {
                alert("Password must be at least 6 characters long, and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
                return false;
            }
            return true;
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
