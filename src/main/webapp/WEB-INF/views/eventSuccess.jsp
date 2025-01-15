<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>New Event Added</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid bg-primary text-white w-100 p-2">
    <div class="text-center">
        <h1 class="m-1" style="font-family: 'Arial', sans-serif; font-weight: bold;">EventApp</h1>
    </div>
</div>
<div class="container mt-5">
    <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Event App</a>

            <div class="nav-item">
                <a class="btn btn-success" href="/home/createEvent" aria-disabled="true">Create Event</a>
            </div>
            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <div class="nav-item">
                    <a class="btn btn-info" href="/admin/home/eventRequests" aria-disabled="true">Event Requests</a>
                </div>
            </c:if>
            <div class="nav-item">
                <a class="btn btn-success" aria-disabled="true" href="/home/registeredEvents?type=organizedEvents">Organized Events</a>
            </div>
            <div class="nav-item">
                <a class="btn btn-success" aria-disabled="true" href="/home/registeredEvents?type=myEvents">Registered Events</a>
            </div>
            <div class="nav-item">
                <a class="btn btn-success" href="/home/profile" aria-disabled="true">Profile</a>
            </div>
            <div class="nav-item">
                <a class="btn btn-danger" href="/logout" aria-disabled="true">Log out</a>
            </div>
        </div>
    </nav>
    <div class="row justify-content-center">

        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center bg-primary text-white">
                    <h2>New Event Added Successfully!</h2>
                </div>
            </div>
            <div class="card">
                <div class="card-header text-center">
                    <a href="/home">Back to Home</a>
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