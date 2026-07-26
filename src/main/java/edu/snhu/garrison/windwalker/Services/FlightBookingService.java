package edu.snhu.garrison.windwalker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.snhu.garrison.windwalker.Model.Booking;
import edu.snhu.garrison.windwalker.Repository.BookingRepository;

/**
 * Service responsible for handling flight booking logic and delegating data
 * access to the repository layer.
 */
@Service
public class FlightBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Saves the booking information to the database.
     *
     * @param booking The Booking object containing booking details.
     */
    public Booking saveBooking(Booking booking) {
        
        // Validate booking object
        if (booking == null) {
            return null;
        }

        // Save booking to the database - Use JPA Repository method.
        return bookingRepository.save(booking);
    }
}
