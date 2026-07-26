package edu.snhu.garrison.windwalker.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Model representing a booking JPA DB entity.
 */
@Entity
@Table(name = "bookings")
public class Booking {

    // Unique identifier for the booking.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username of the user who made the booking - From session User.
    String username;

    // Passenger Details - From PassengerData Booking Data.
    String passengerFirstName;
    String passengerLastName;
    String passengerEmail;
    String passengerPhone;

    //From session User.username - Flight Option
    int flightId;

    LocalDateTime bookedAt;

    // Constructors
    public Booking() {
    }

    // Overloaded Constructor
    public Booking(String username, String passengerFirstName, String passengerLastName, String passengerEmail, String passengerPhone, int flightId, LocalDateTime bookedAt) {
        this.username = username;
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.passengerEmail = passengerEmail;
        this.passengerPhone = passengerPhone;
        this.flightId = flightId;
        this.bookedAt = bookedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

}
