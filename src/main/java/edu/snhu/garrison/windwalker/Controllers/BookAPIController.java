package edu.snhu.garrison.windwalker.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.snhu.garrison.windwalker.Model.FlightSearchRequest;

@RestController
@RequestMapping("api/booking")
public class BookAPIController {
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
