<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>EventBrite Lite App - Login</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div>
    <div class="container-fluid bg-primary text-white w-100 p-2">
        <div class="text-center">
            <h1 class="m-1" style="font-family: 'Arial', sans-serif; font-weight: bold;">EventApp</h1>
        </div>
    </div>
    <div class="container mt-5">
        <c:if test="${not empty message and message.length() > 0}">
            <div class="alert alert-success" role="alert">
                    ${message}
            </div>
        </c:if>
        <c:if test="${not empty error and error.length() > 0}">
            <div class="alert alert-info" role="alert">
                    ${error}
            </div>
        </c:if>
        <c:if test="${not empty validationError }">
            <div class="alert alert-danger" role="alert">
                Login Failed:
                <c:forEach var="err" items="${validationError}">
                    <li>${err.defaultMessage}</li>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <div class="row justify-content-center mt-4 w-100 p-3">
        <div class="col-md-4">
            <div class="card shadow">
                <!-- Login Form Header -->
                <div class="card-header text-center">
                    <h4>Log In</h4>
                </div>
                <!-- Login Form -->
                <div class="card-body">
                    <form action="/api/users/login" method="post">
                        <div class="form-group mb-3">
                            <label for="email" class="col-form-label">Email:</label>
                            <input type="email" id="email" class="form-control mt-2" name="email" placeholder="id@mail.com" required />
                        </div>
                        <div class="form-group mb-3">
                            <label for="password" class="col-form-label">Password:</label>
                            <input type="password" id="password" class="form-control mt-2" name="password" placeholder="id@mail.com" required />
                        </div>
                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
                </div>

                <!-- Registration Link -->
                <div class="card-footer text-center">
                    <h5>New User? <a href="/register">Register Here</a></h5>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Include Bootstrap JS (Optional, for interactivity) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
