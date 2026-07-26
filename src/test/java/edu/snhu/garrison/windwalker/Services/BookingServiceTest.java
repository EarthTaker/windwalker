package edu.snhu.garrison.windwalker.Services;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import edu.snhu.garrison.windwalker.Repository.BookingRepository;
import edu.snhu.garrison.windwalker.Model.Booking;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

//Enable Mockito in JUnit tests
@ExtendWith(MockitoExtension.class)

//Booking Service Test Class    
public class BookingServiceTest {

    //Mock BookingRepository - Fake Repository.
    @Mock
    private BookingRepository bookingRepository;

    //Mock FlightBookingService - Uses a real Service with a fake Repository.
    @InjectMocks
    private FlightBookingService flightBookingService;

    /**
     * Tests - Should successfully book a flight when valid data is provided.
     */
    @Test
    void bookFlight_shouldBookFlight_whenValid() {

        //Generate Booking Object with valid data.
        Booking booking = new Booking("cyberPunk2077", "Johnny", "Silverhand", "garrison.geho@snhu.edu", "660-415-1271", 42, LocalDateTime.now());

        //Mock Repository Save Call - Establishes expected behavior of repository.
        when(bookingRepository.save(booking)).thenReturn(booking);

        //Call to the Booking Service Method to save booking.
        Booking bookingResult = flightBookingService.saveBooking(booking);

        //Assert that the booking object manually created is equal to the booking object returned by the repository.
        assertEquals(bookingResult, booking);

        //Check that the call to the Booking Repository was made (behavior) to verify save was called.
        verify(bookingRepository).save(booking);

    }
}
