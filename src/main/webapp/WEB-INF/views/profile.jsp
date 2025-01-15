<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>User Profile</title>
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
    <c:if test="${not empty message && message.length() > 0}">
        <div class="alert alert-success" role="alert">
                ${message}
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
            </div>
        </div>
        <div class="col-md-8">
            <div class="card">
                <div class="card-header text-center">
                    <h3>User Profile</h3>
                </div>
                <div class="card-body">
                    <!-- Check if the user is present in the session -->
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <p class="text-center">Welcome, <strong>${sessionScope.user.firstName}</strong></p>
                            <table class="table table-bordered mt-4">
                                <thead>
                                <tr>
                                    <th>Field</th>
                                    <th>Value</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>First Name</td>
                                    <td>${sessionScope.user.firstName}</td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td>${sessionScope.user.lastName}</td>
                                </tr>
                                <tr>
                                    <td>Phone</td>
                                    <td>${sessionScope.user.phoneNumber}</td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td>${sessionScope.user.email}</td>
                                </tr>
                                <tr>
                                    <td>Password</td>
                                    <td>${sessionScope.user.password}</td>
                                </tr>
                                </tbody>
                            </table>

                            <!-- Primary Address -->
                            <c:if test="${not empty sessionScope.user.primaryAddress}">
                                <h5 class="mt-4">Primary Address</h5>
                                <p>
                                    <strong>Street:</strong> ${sessionScope.user.primaryAddress.street}<br>
                                    <strong>City:</strong> ${sessionScope.user.primaryAddress.city}<br>
                                    <strong>State:</strong> ${sessionScope.user.primaryAddress.state}<br>
                                    <strong>Zip:</strong> ${sessionScope.user.primaryAddress.zip}<br>
                                    <strong>Country:</strong> ${sessionScope.user.primaryAddress.country}
                                </p>
                            </c:if>

                            <!-- Secondary Address -->
                            <c:if test="${not empty sessionScope.user.secondaryAddress}">
                                <h5 class="mt-4">Secondary Address</h5>
                                <p>
                                    <strong>Street:</strong> ${sessionScope.user.secondaryAddress.street}<br>
                                    <strong>City:</strong> ${sessionScope.user.secondaryAddress.city}<br>
                                    <strong>State:</strong> ${sessionScope.user.secondaryAddress.state}<br>
                                    <strong>Zip:</strong> ${sessionScope.user.secondaryAddress.zip}<br>
                                    <strong>Country:</strong> ${sessionScope.user.secondaryAddress.country}
                                </p>
                            </c:if>

                            <!-- No Address Message -->
                            <c:if test="${empty sessionScope.user.primaryAddress && empty sessionScope.user.secondaryAddress}">
                                <p class="mt-4">No address available.</p>
                            </c:if>

                            <a href="/home/profile/edit" class="btn btn-primary mt-4">Edit</a>
                        </c:when>
                        <c:otherwise>
                            <p class="text-center">No user logged in. Please log in.</p>
                        </c:otherwise>
                    </c:choose>
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
