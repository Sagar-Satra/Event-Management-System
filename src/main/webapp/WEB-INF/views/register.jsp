<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>EventApp - User Registration</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid bg-primary text-white w-100 p-2">
    <div class="text-center">
        <h1 class="m-1" style="font-family: 'Arial', sans-serif; font-weight: bold;">EventApp</h1>
    </div>
</div>
<div class="container mt-3">
    <c:if test="${not empty validationError }">
        <div class="alert alert-danger" role="alert">
            Validation Failed:
            <c:forEach var="err" items="${validationError}">
                <li>${err.defaultMessage}</li>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${not empty error and error.length() > 0}">
        <div class="alert alert-info" role="alert">
                ${error}
        </div>
    </c:if>
    <div class="row justify-content-center p-2">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header text-center">
                    <h4>Register</h4>
                </div>
                <div class="card-body p-3">
                    <form action="/api/users/register" method="post">
                        <div class="form-group mb-2">
                            <label for="firstName" class="col-form-label">First Name:</label>
                            <input type="text" class="form-control mt-1" id="firstName" name="firstName" placeholder="First Name" required />
                        </div>
                        <div class="form-group mb-2">
                            <label for="lastName" class="col-form-label">Last Name:</label>
                            <input type="text" class="form-control mt-1" id="lastName" name="lastName" placeholder="Last Name" required />
                        </div>
                        <div class="form-group mb-2">
                            <label for="email" class="col-form-label">Email Address:</label>
                            <input type="email" class="form-control mt-1" id="email" name="email" placeholder="id@mail.com" required />
                        </div>
                        <div class="form-group mb-2">
                            <label for="phoneNumber" class="col-form-label">Phone Number:</label>
                            <input type="number" class="form-control mt-1" id="phoneNumber" name="phoneNumber" placeholder="1234567890" required />
                        </div>
                        <div class="form-group mb-2">
                            <label for="password" class="col-form-label">Password:</label>
                            <input type="password" class="form-control mt-1" id="password" name="password" placeholder="password" required />
                        </div>
                        <div class="text-center mt-2">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <h5><a href="/login">Back to Login Page</a></h5>
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