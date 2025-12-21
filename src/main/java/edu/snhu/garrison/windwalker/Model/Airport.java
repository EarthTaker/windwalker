package edu.snhu.garrison.windwalker.Model;

/*
* DTO to act as container for dynamically generated Airports loaded into the Repo.
*/
public final class Airport {
    private final String airportCode;
    private final String name;
    private final String city;
    private final String country;

    public Airport(String airportCode, String name, String city, String country) {
        this.airportCode = airportCode;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    //Getters and Setters
    public String getAirportCode(){
        return airportCode;
    };

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}

