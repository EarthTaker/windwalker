package edu.snhu.garrison.windwalker.Controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.snhu.garrison.windwalker.Model.Booking;
import edu.snhu.garrison.windwalker.Model.FlightOption;
import edu.snhu.garrison.windwalker.Model.PassengerData;
import edu.snhu.garrison.windwalker.Services.FlightBookingService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/booking")
public class BookAPIController {

    //Inject FlightBookingService via Autowired
    @Autowired
    private FlightBookingService bookingService;

    /**
     * Method - Stores the selected flight in the user's session, upholds PRG
     * pattern.
     *
     * @param req - RequestBody - Re-builds Flight Option DTO using inbound HTTP
     * Request
     * @return - ResponseEntity - Wrapper for HTTP Response
     */
    @PostMapping("/flight")
    public ResponseEntity<Void> bookFlight(@RequestBody FlightOption req, HttpSession session) {

        // Store the selected flight in the session to be accessed later by Booking view.
        session.setAttribute("selectedFlight", req);

        return ResponseEntity.ok().build();
    }

    /**
     * Method - Confirms the booking of the selected flight.
     */
    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmBooking(@RequestBody PassengerData req, HttpSession session, Authentication auth) {

        //Get flight from session.
        FlightOption selectedFlight = (FlightOption) session.getAttribute("selectedFlight");

        //Create new Booking object.
        Booking booking = new Booking(auth.getName(), req.getFirstName(), req.getLastName(), req.getEmail(), req.getPhone(), selectedFlight.getId(), LocalDateTime.now());

        //Call booking service to save booking.
        bookingService.saveBooking(booking);

        // Clear the selected flight from the session after booking confirmation.
        session.removeAttribute("selectedFlight");

        return ResponseEntity.ok().build();
    }

}
