package edu.snhu.garrison.windwalker.Model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
* DTO acting as a container for any returning flight options pulled from the repository.
* 
 */
@Entity
@Table(name = "flights")
public class FlightOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String airlineName; // Ex: Straubing Airport, Beijing Daxing International Airport
    private String airportCode; // Ex: RBM, PKX
    private String flightNumber; // Ex: WN2544, SK9521
    private LocalTime arriveTime; // Ex: 12:11 AM, 1:14 PM
    private LocalTime departureTime; // Ex: 1:11 AM, 3:45 PM
    private int flightDuration; // Ex: 1.5 Hrs, 2 Hrs
    private double price; // Ex: 7.99, 800.00
    private String airportArriveLocation; // Ex: San Francisco International
    private String arriveCity; // Ex: San Francisco
    private String arriveCountry; // Ex: USA
    private String airportDepartureLocation; // Ex: Los Angeles International
    private String departureCity; // Ex: Los Angeles
    private String departureCountry; // Ex: USA
    private int numPassengers; // 1, 2, etc.
    private String seatClass; // First Class, Coach, etc.

    // Requried for JPA
    public FlightOption() {
    }

    // DB ID Only Constructor
    public FlightOption(int id) {
        this.id = id;
    }

    // Populated Flight Constructor
    public FlightOption(String airlineName, String airportCode, String flightNumber, LocalTime arriveTime,
            LocalTime departureTime, int flightDuration, double price, String airportArriveLocation, String arriveCity,
            String arriveCountry, String airportDepartureLocation, String departureCity, String departureCountry, int numPassengers, String seatClass) {
        this.airlineName = airlineName;
        this.airportCode = airportCode;
        this.flightNumber = flightNumber;
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.flightDuration = flightDuration;
        this.price = price;
        this.airportArriveLocation = airportArriveLocation;
        this.arriveCity = arriveCity;
        this.arriveCountry = arriveCountry;
        this.airportDepartureLocation = airportDepartureLocation;
        this.departureCity = departureCity;
        this.departureCountry = departureCountry;
        this.numPassengers = numPassengers;
        this.seatClass = seatClass;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getAirportDepartureLocation() {
        return airportDepartureLocation;
    }

    public void setAirportDepartureLocation(String airportDepartureLocation) {
        this.airportDepartureLocation = airportDepartureLocation;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAirportArriveLocation() {
        return airportArriveLocation;
    }

    public void setAirportArriveLocation(String airportArriveLocation) {
        this.airportArriveLocation = airportArriveLocation;
    }

    public String getArriveCity() {
        return arriveCity;
    }

    public void setArriveCity(String arriveCity) {
        this.arriveCity = arriveCity;
    }

    public String getArriveCountry() {
        return arriveCountry;
    }

    public void setArriveCountry(String arriveCountry) {
        this.arriveCountry = arriveCountry;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public void setDepartureCountry(String departureCountry) {
        this.departureCountry = departureCountry;
    }

}
