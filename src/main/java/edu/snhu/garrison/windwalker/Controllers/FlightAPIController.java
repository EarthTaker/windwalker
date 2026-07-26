package edu.snhu.garrison.windwalker.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.snhu.garrison.windwalker.Model.FlightOption;
import edu.snhu.garrison.windwalker.Model.FlightSearchRequest;
import edu.snhu.garrison.windwalker.Services.FlightSearchService;

@RestController
@RequestMapping("/api/flights")
public class FlightAPIController {

    // Automatically injects the UserAuthenticationService bean into the controller
    // at runtime.
    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * API Endpoint to handle client requests for flight searches.
     * 
     * RESTful Practice: uses @GetMapping for read-only operations.
     * URL: /api/flights/search
     * 
     * @param from          Origin city name
     * @param to            Destination city name
     * @param departureDate Date of departure
     * @param returnDate    Optional return date
     * @param passengers    Number of passengers
     * @param tripType      OneWay or RoundTrip
     * @param seatClass     Economy, Business, or First
     * @return A list of FlightOptions.
     */
    @GetMapping("/search")
    public List<FlightOption> searchFlights(
            @RequestParam("from") String from, @RequestParam("to") String to,
            @RequestParam("departureDate") String departureDate, @RequestParam(value = "returnDate", required = false) String returnDate,
            @RequestParam("passengers") int passengers, @RequestParam("tripType") String tripType,
            @RequestParam("seatClass") String seatClass) {

        // Generate a DTO to encapsulate parameters
        FlightSearchRequest req = new FlightSearchRequest(to, from, departureDate, returnDate, tripType, passengers,
                seatClass);

        // Call to the service method, pass the request, return the expected list of
        // flights.
        List<FlightOption> results = flightSearchService.searchFlights(req);

        return results;
    }

}