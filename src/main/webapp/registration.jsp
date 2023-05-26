<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css">

    <title>Register User</title>
    <style>
        .container {
            width: 700px;
        }
    </style>

</head>
<body>
    <jsp:include page="/common/header.jsp" />

    <div class="container d-flex justify-content-center align-items-center vh-100 text-center">

        <form class="login-form" method="post" action="controller">
            <div class="form-floating flex-grow-1 mb-3">
                <h4>Registration form</h4>
            </div>
            <input type="hidden" name="action" value="registration">

            <div class="form-floating flex-grow-1 mb-3">
                <input type="text" class="form-control" id="firstname" name="firstname"
                       placeholder="John" required>
                <label for="firstname">First name:</label>
            </div>

            <div class="form-floating flex-grow-1 mb-3">
                <input type="text" class="form-control" id="lastname" name="lastname"
                       placeholder="Brown" required>
                <label for="lastname">Last name:</label>
            </div>

            <div class="form-floating flex-grow-1 mb-3">
                <input type="email" class="form-control" id="email" name="email"
                       placeholder="example@mail.com" required>
                <label for="email">Email:</label>
            </div>


            <div class="form-floating flex-grow-1 mb-3">
                <input type="text" class="form-control" id="phone_number" name="phone_number"
                       placeholder="+18937411243">
                <label for="phone_number">Phone number:</label>
            </div>

            <div class="d-flex flex-grow-1 align-items-center">
                <div class="form-floating me-2">
                    <input type="password" class="form-control" id="password" placeholder="Password"
                           name="password" required>
                    <label for="password">Password:</label>
                </div>

                <div class="form-floating ms-2">
                    <input type="password" class="form-control" id="confirm" placeholder="Password"
                           name="confirm" required>
                    <label for="confirm">Confirm:</label>
                </div>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Submit</button>
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
