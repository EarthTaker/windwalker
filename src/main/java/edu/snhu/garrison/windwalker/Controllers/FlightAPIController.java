package edu.snhu.garrison.windwalker.Controllers;

import java.util.List;
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

    private FlightSearchService flightSearchService;

    public FlightAPIController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    /**
     * API Endpoint to handle client requests for each flight search.
     * Mapping - /search
     * 
     * @return A list of FlightOptions pulled from the repository.
     * 
     */
    @GetMapping("/search")
    public List<FlightOption> searchFlights(
            @RequestParam() String from, @RequestParam() String to,
            @RequestParam() String departureDate, @RequestParam(required = false) String returnDate,
            @RequestParam() int passengers, @RequestParam() String tripType,
            @RequestParam() String seatClass) {

        // Generate a DTO
        FlightSearchRequest req = new FlightSearchRequest(to, from, departureDate, returnDate, tripType, passengers,
                seatClass);

        List<FlightOption> results = flightSearchService.searchFlights(req);

        return results;
    }

    /**
     * API Endpoint to allow a logged in user to book a flight.
     * Mapping - /book
     * 
     * @return
     * 
     */
    @GetMapping("/book")
    public void bookFlight(
            @RequestParam() String from, @RequestParam() String to,
            @RequestParam() String departureDate, @RequestParam(required = false) String returnDate,
            @RequestParam() int passengers, @RequestParam() String tripType,
            @RequestParam() String seatClass) {

        FlightSearchRequest req = new FlightSearchRequest(to, from, departureDate, returnDate, tripType, passengers,
                seatClass);

    }
}
