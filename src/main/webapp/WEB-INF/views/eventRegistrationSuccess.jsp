<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="/eventImages/favicon-eventApp.png" type="image/png">
    <title>New User Added</title>
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
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center bg-success text-white">
                    <h2>You have successfully registered for the event.</h2>
                </div>
                <div class="card-body text-center">
                  <form action="/api/event/ticket?eventId=${eventID}&userId=${userID}" method="POST">
                        <button type="submit" class="btn btn-primary">
                            Download Ticket
                        </button>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="/home" class="btn btn-link">Back to Home Page</a>
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