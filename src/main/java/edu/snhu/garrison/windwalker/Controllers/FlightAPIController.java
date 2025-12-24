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
            @RequestParam() String from, @RequestParam() String to,
            @RequestParam() String departureDate, @RequestParam(required = false) String returnDate,
            @RequestParam() int passengers, @RequestParam() String tripType,
            @RequestParam() String seatClass) {

        // Generate a DTO to encapsulate parameters
        FlightSearchRequest req = new FlightSearchRequest(to, from, departureDate, returnDate, tripType, passengers,
                seatClass);

        // Call to the service method, pass the request, return the expected list of
        // flights.
        List<FlightOption> results = flightSearchService.searchFlights(req);

        return results;
    }

    /**
     * API Endpoint to allow a logged in user to book a flight.
     * 
     * @apiNote Verification of Booking implementation is deferred to future
     *          milestones.
     * 
     */
    @GetMapping("/book")
    public void bookFlight(
            @RequestParam() String from, @RequestParam() String to,
            @RequestParam() String departureDate, @RequestParam(required = false) String returnDate,
            @RequestParam() int passengers, @RequestParam() String tripType,
            @RequestParam() String seatClass) {

        // TODO: Implement Booking Service logic in Milestone 3
        FlightSearchRequest req = new FlightSearchRequest(to, from, departureDate, returnDate, tripType, passengers,
                seatClass);

    }
}
