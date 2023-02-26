<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <title>Register User</title>
    <style>
        .error { color: red; }
    </style>

</head>
<body>

   <form action="controller">
       <input type="submit" class="button_active" value="Logout" style="align: right;">
       <input type="hidden" name="action" value="logout">
   </form>


    <div class="container" id="div-form" style="width: 500px; margin: auto; align-self: center">
        <form class="login-form" method="post" action="controller">
            <input type="hidden" name="action" value="register">

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
            <button type="submit">Register</button>

            <div class="container">
                <button type="button" class="cancel-btn">Cancel</button>
                <span class="pass">Forgot <a href="#">password?</a></span>
            </div>
        </form>
    </div>
   <p class="error">${sessionScope.error}</p>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
