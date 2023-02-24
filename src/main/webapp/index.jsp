<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Test page</title>
</head>
<body>
<h1><%= "Test from JSP!" %>
</h1>
<br/>
    <a href="test">Test Servlet</a> <br>
    <a href="choose-files">Choose photos</a>

    <br>
    <form method="get" action="controller">
        <input type="hidden" name="command" value="home">

        <label class="form-label" for="email">Email:</label>
        <input class="form-control" type="email" name="email" id="email" required>

        <button type="submit" class="btn">Submit</button>
    </form>

    <form method="POST" action="choose-files"  id="form1">
        <label for="filename">Choose photos:</label>
        <input type="text" id="filename" name="file-dialog" value="c:\photo.jpg"><br><br>

    </form>

    <button type="submit" form="form1" value="Submit"> Submit </button>

</body>
</html>