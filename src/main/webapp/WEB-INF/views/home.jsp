<%@ page import="com.eventmanagement.eventapp.models.Event" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>Home Page: Events Happening</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .event-card {
            margin-bottom: 20px;
        }
        .event-card img {
            max-width: 100%;
            height: auto;
        }
        .event-details {
            padding: 20px;
        }
        .event-title {
            font-size: 1.5em;
            font-weight: bold;
        }
        .event-description {
            font-size: 1em;
            color: #555;
        }
        .event-info {
            margin-top: 15px;
        }
        .register-btn {
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="container-fluid bg-primary text-white w-100 p-2">
    <div class="text-center">
        <h1 class="m-1" style="font-family: 'Arial', sans-serif; font-weight: bold;">EventApp</h1>
    </div>
</div>
<div class="container mt-5">
    <c:if test="${not empty message && message.length() > 0}">
        <div class="alert alert-success" role="alert">
                ${message}
        </div>
    </c:if>
    <c:if test="${not empty error and error.length() > 0}">
        <div class="alert alert-warning" role="alert">
                ${error}
        </div>
    </c:if>
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card">
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
                <form action="/api/event/search" method="post">
                    <input type="text" class="form-control" name="title" placeholder="Search Title" />
                    <button class="btn btn-primary">Search</button>
                </form>
                <div class="card-body">
                    <h3 class="text-center mb-4">Events Happening</h3>
                    <c:forEach var="event" items="${events}">
                        <div class="event-card card">
                            <div class="row no-gutters">
                                <div class="col-md-4">
                                    <img src="${event.picture}" class="card-img" alt="${event.title}">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body event-details">
                                        <h5 class="event-title">${event.title}</h5>
                                        <p class="event-description">${event.description}</p>
                                        <div class="event-info">
                                            <p><strong>Location:</strong> ${event.location.street}, ${event.location.city}, ${event.location.state}, ${event.location.zip}, ${event.location.country}</p>
                                            <p><strong>Organizer:</strong> ${event.organizer.firstName} ${event.organizer.lastName}</p>
                                            <p><strong>Date</strong> ${event.date}</p>
                                            <p><strong>Start Time:</strong> ${event.startTime}</p>
                                            <p><strong>End Time:</strong> ${event.endTime}</p>
                                            <p><strong>Status:</strong> ${event.status}</p>
                                            <h4><strong> ${event.capacity} seats left</strong></h4>
                                            <h4><strong> ${event.participants.size()} have registered ! </strong></h4>
                                        </div>
                                        <c:if test="${sessionScope.user.role != 'ADMIN'}">
                                            <div class="register-btn">
                                                <form action="/api/event/${event.id}/register" method="POST">
                                                    <button
                                                            type="submit"
                                                            class="btn btn-primary"
                                                        ${event.capacity == 0 ? 'disabled' : ''}>
                                                        Register
                                                    </button>
                                                </form>
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.user.role == 'ADMIN'}">
                                            <a href="/home/registeredEvents/participants?eventId=${event.id}" class="btn btn-primary">View Participants</a>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
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
