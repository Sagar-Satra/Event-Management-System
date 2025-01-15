<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>User Profile Edit</title>
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
    <c:if test="${not empty validationError }">
        <div class="alert alert-danger" role="alert">
            Edit Profile Failed:
            <c:forEach var="err" items="${validationError}">
                <li>${err.defaultMessage}</li>
            </c:forEach>
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
        <div class="col-md-8">
            <div class="card">
                <div class="card-header text-center">
                    <h3>Edit User Profile</h3>
                </div>
                <div class="card-body">
                    <form method="post" action="/api/users/${sessionScope.user.id}">
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" name="firstName" class="form-control" id="firstName" value="${sessionScope.user.firstName}" />
                        </div>

                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" name="lastName" class="form-control" id="lastName" value="${sessionScope.user.lastName}" />
                        </div>

                        <div class="form-group">
                            <label for="phoneNumber">Phone Number</label>
                            <input type="tel" name="phoneNumber" class="form-control" id="phoneNumber" value="${sessionScope.user.phoneNumber}" />
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" class="form-control" id="password" value="${sessionScope.user.password}"  />
                        </div>

                        <!-- Address Fields -->
                        <h5>Primary Address</h5>
                        <div class="form-group">
                            <label for="primaryStreet">Street</label>
                            <input type="text" name="primaryAddress.street" class="form-control" value="${sessionScope.user.primaryAddress.street}" id="primaryStreet" required />
                        </div>

                        <div class="form-group">
                            <label for="primaryCity">City</label>
                            <input type="text" name="primaryAddress.city" class="form-control" value="${sessionScope.user.primaryAddress.city}" id="primaryCity" required />
                        </div>

                        <div class="form-group">
                            <label for="primaryState">State</label>
                            <input type="text" name="primaryAddress.state" class="form-control" value="${sessionScope.user.primaryAddress.state}" id="primaryState" required />
                        </div>

                        <div class="form-group">
                            <label for="primaryZip">Zip</label>
                            <input type="number" name="primaryAddress.zip" class="form-control" value="${sessionScope.user.primaryAddress.zip}" id="primaryZip" required />
                        </div>

                        <div class="form-group">
                            <label for="primaryCountry">Country</label>
                            <input type="text" name="primaryAddress.country" class="form-control" value="${sessionScope.user.primaryAddress.country}" id="primaryCountry" />
                        </div>

                        <h5>Secondary Address</h5>
                        <div class="form-group">
                            <label for="secondaryStreet">Street</label>
                            <input type="text" name="secondaryAddress.street" class="form-control" value="${sessionScope.user.secondaryAddress.street}" id="secondaryStreet"  />
                        </div>

                        <div class="form-group">
                            <label for="secondaryCity">City</label>
                            <input type="text" name="secondaryAddress.city" class="form-control" value="${sessionScope.user.secondaryAddress.city}" id="secondaryCity" />
                        </div>

                        <div class="form-group">
                            <label for="secondaryState">State</label>
                            <input type="text" name="secondaryAddress.state" class="form-control" value="${sessionScope.user.secondaryAddress.state}" id="secondaryState" />
                        </div>

                        <div class="form-group">
                            <label for="secondaryZip">Zip</label>
                            <input type="number" name="secondaryAddress.zip" class="form-control" value="${sessionScope.user.secondaryAddress.zip}" id="secondaryZip" />
                        </div>

                        <div class="form-group">
                            <label for="secondaryCountry">Country</label>
                            <input type="text" name="secondaryAddress.country" class="form-control" value="${sessionScope.user.secondaryAddress.country}" id="secondaryCountry" />
                        </div>


                        <button type="submit" class="btn btn-primary">Update Profile</button>
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
