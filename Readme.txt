# Windwalker Flight Reservation Application

## Overview
Windwalker is a web-based flight reservation prototype developed for my IT-634 Course at Southern New Hampshire University. Users can register, log in, search a preloaded collection of flights, select a flight, and confirm their booking. This project was created to develop my understanding of RESTful API design, layered Spring Boot architecture, server-side rendering, database persistence, and application security.

## Features

   - User registration and login
   - REST-based flight searching
   - Filtering by departure and arrival city
   - In-memory database-backed flight records
   - Flight selection and booking confirmation
   - Server-rendered pages using Thymeleaf

### User Workflow
Users can create an account, log in, and search a preloaded collection of flights. For example, users can search for a flight from New York to Los Angeles. After selecting a flight, the user must include at least one passenger before confirming the booking.

Test Flight - 

From: New York 
To: Los Angeles

## Technologies Used

### Languages
   - Java 17
   - HTML
   - CSS
   - JavaScript

### Backend
   - Spring Boot
   - Spring MVC
   - Spring Data JPA
   - Spring Security

### Frontend
   - Thymeleaf
   - Bootstrap

### Database
   - H2 In-Memory Database

### Build Tool
   - Gradle

### Testing
   - JUnit
   - Mockito

## Application Architecture
   - View Layer - Displays server-rendered pages using Thymeleaf templates and Bootstrap for styling. 
   - Controller Layer - Receives browser requests and REST API requests.
   - Service Layer - Contains business logic and flight-search business logic.
   - Model Layer - Contains data models, DTOs, and entities representing users, authentication requests, flights, airports, and airlines.
   - Repository Layer - Retrieves and stores data using Spring Data JPA and the H2 In-Memory Database. Sample flight records are generated at runtime and stored in H2.

## Running the Application

   1. Clone the repository or download and extract the ZIP file.
   2. Confirm Java 17 is installed. 
   3. Open a terminal at the project root.
   4. Run the application: .\gradlew bootRun
   5. Open: http://localhost:8080/

## API Endpoints

Method         Endpoint             Purpose
searchFlights  /api/flights/search  Search for available flights.
bookFlight     /api/booking/flight  Splash page for booking a user-selected flight.
confirmBooking /api/booking/confirm Form submission for booking a user-selected flight.

TODO:
## Database
## What I Implemented
## What I Learned
## Current Status and Limitations
