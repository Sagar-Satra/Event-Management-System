<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>Create New Event - Step 2</title>
    <!-- Included Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid bg-primary text-white w-100 p-2">
    <div class="text-center">
        <h1 class="m-1" style="font-family: 'Arial', sans-serif; font-weight: bold;">EventApp</h1>
    </div>
</div>
<div class="container mt-5">
    <c:if test="${not empty validationError }">
        <div class="alert alert-warning" role="alert">
            Event Registration Failed:
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
    <div class="row justify-content-center">
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
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center text-white bg-primary">
                    <h4>Create Event</h4>
                </div>
                <div class="card-body">
                    <form action="/api/event/createEvent?step=2" enctype="multipart/form-data" method="post">
                            <input type="hidden" value="${sessionScope.event.title}" class="form-control" id="title" name="title" placeholder="Add a Title" />

                            <input type="hidden" value="${sessionScope.event.description}"  class="form-control" id="description" name="description" placeholder="Description" />

                            <input type="hidden" value="${sessionScope.event.capacity}" class="form-control" id="capacity" name="capacity" placeholder="Capacity" />

                            <input type="hidden" value="${sessionScope.event.date}" class="form-control" id="date" name="date" />

                            <input type="hidden" value="${sessionScope.event.startTime}" class="form-control" id="startTime" name="startTime" />

                            <input type="hidden" value="${sessionScope.event.endTime}"  class="form-control" id="endTime" name="endTime" />

                            <label for="picture">Picture</label>
                            <input type="file" class="form-control" id="picture" name="picture" />
<%--                            <input type="file" value="${sessionScope.event.picture}" class="form-control" id="picture" name="picture" />--%>
                            <br>
                            <label for="address">Select Address</label>
                            <select class="form-control" id="address" name="location" required>
                                <option value="" disabled selected>Select an address</option>
                                <c:forEach var="address" items="${addresses}">
                                    <option value="${address.id}">
                                            ${address.street}, ${address.city}, ${address.state}, ${address.zip}, ${address.country}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Create Event</button>
                    </form>
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
