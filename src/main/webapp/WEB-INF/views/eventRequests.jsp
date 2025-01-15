<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>Pending Event Requests</title>
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
        <h2 class="mb-4">Event Requests Pending</h2>
    </div>
    <c:if test="${not empty message and message.length() > 0}">
        <div class="alert alert-success text-center" role="alert">
                ${message}
        </div>
    </c:if>
    <c:if test="${empty pendingEvents}">
        <div class="alert alert-info justify-content-center" role="alert">
            <p class="text-center">No new event request</p>
        </div>
    </c:if>
    <div class="row">
        <c:forEach var="event" items="${pendingEvents}">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="${event.picture}" class="card-img-top" alt="Picture for ${event.title}" style="height: 200px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title">${event.title}</h5>
                        <p class="card-text">${event.description}</p>
                        <p class="card-text">
                            <strong>Location:</strong>
                                ${event.location.street}, ${event.location.city},
                                ${event.location.state}, ${event.location.zip},
                                ${event.location.country}
                        </p>
                        <p class="card-text">
                            <strong>Organizer:</strong> ${event.organizer.firstName} ${event.organizer.lastName}
                        </p>
                        <p class="card-text"><strong>Email:</strong> ${event.organizer.email}</p>
                        <p class="card-text"><strong>Phone:</strong> ${event.organizer.phoneNumber}</p>
                        <p class="card-text"><strong>Capacity:</strong> ${event.capacity}</p>
                        <p class="card-text"><strong>Date:</strong> ${event.date}</p>
                        <p class="card-text"><strong>Start Time:</strong> ${event.startTime}</p>
                        <p class="card-text"><strong>End Time:</strong> ${event.endTime}</p>
                        <div class="d-flex gap-2">
                            <form action="/api/admin/event/${event.id}/approve" method="post">
                                <button type="submit" class="btn btn-primary">Approve</button>
                            </form>
                            <form action="/api/admin/event/${event.id}/reject" method="post">
                                <button type="submit" class="btn btn-danger">Reject</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Include Bootstrap JS (Optional, for interactivity) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
