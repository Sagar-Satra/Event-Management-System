# Event Management System

This is an Event Management System built using Spring Boot and the Spring Framework.

## Project Overview

The Event Management System (EMS) is a web-based application designed to facilitate event creation, user registration, and venue management. It can be used internally by universities, faculty, clubs, and student boards for day-to-day events, as well as by outside organizations to host events.

## Objectives

- **Simplified Event Creation and Management**: 
  - Any user role (University official, Faculty, Clubs, Employers, Students Boards) can create new event requests.
  - Events are created based on details like agenda, participant capacity, guests, and venue availability within the university.

- **Primary Administrator Access**:
  - Administrators can view newly created event requests and approve them based on user requirements.

- **Streamline User Registration**:
  - Users can search for events by category, register for events, view event details, and receive updates on their registration status when they log in.

- **Event Admin**:
  - The user who created the event can view participants' registration details and manage event-specific information.

- **Efficient Venue Management**:
  - Administrators can reschedule venues for events based on capacity and availability.

- **User Registration View and Profile Management**:
  - Users can manage their profile, view upcoming events, and check their history of attended events.

## Technologies Used

- **Backend**: Spring Boot, Spring Framework
- **Frontend**: To be determined (e.g., Angular, React, or Thymeleaf)
- **Database**: To be determined (e.g., MySQL, PostgreSQL, or MongoDB)
- **Build Tool**: Maven/Gradle

## Features

- Role-based access control for users, event admins, and administrators.
- Notification system for event status updates.
- Real-time venue availability and rescheduling.
- Seamless user experience for event search and registration.
- Efficient management of event waitlists and participant details.

## Setup Instructions

1. Build and run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

2. Access the application in your browser at `http://localhost:8080`.

OR

1. Download SpringBoot supporting libraries from Official website and make use of any IDEs like Eclipse or NetBeans or IntelliJ IDEA to run the program directly.

## Contribution Guidelines

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

## Warning

This project is intended for learning and personal use only. Using this project for academic purposes without proper attribution may lead to plagiarism, which is a violation of academic integrity policies.

## License

This project is licensed under the [MIT License](LICENSE).

---
