package edu.snhu.garrison.windwalker.Model;

/**
 * DTO acts as a container for the user's initial flight search request. 
 */
public class FlightSearchRequest {

    // Class Attributes
    private String to;
    private String from;
    private String departureDate;
    private String returnDate;
    private String tripType;
    private int passengers;
    private String seatClass;

    // Zero Argument Constructor
    public FlightSearchRequest() {
    }

    // Constructor
    public FlightSearchRequest(String from, String to, String departureDate, String returnDate, String tripType,
            int passengers, String seatClass) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.tripType = tripType;
        this.passengers = passengers;
        this.seatClass = seatClass;
    }

    // Getters and Setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
}
