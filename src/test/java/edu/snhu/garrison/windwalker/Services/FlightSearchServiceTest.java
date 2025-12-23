package edu.snhu.garrison.windwalker.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import edu.snhu.garrison.windwalker.Repository.FlightRepository;
import edu.snhu.garrison.windwalker.Model.FlightSearchRequest;
import edu.snhu.garrison.windwalker.Model.FlightOption;
import java.util.List;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;

// 1. SETUP: We use specific tools (annotations) to help us fake the database.
@ExtendWith(MockitoExtension.class)
public class FlightSearchServiceTest {

    // Creates an empty object of the class to test.
    @Mock
    private FlightRepository flightRepository;

    // Creates a copy of the class for testing, but for its repository dependency,
    // it targets the mock object.
    @InjectMocks
    private FlightSearchService flightSearchService;

    /**
     * Tests whether the searchFlights service method returns a list of flights
     * after calling to the repository.
     */
    @Test
    void searchFlights_shouldReturnFlights_whenFound() {

        // Generate a request.
        FlightSearchRequest request = new FlightSearchRequest("Boston", "London", "2025-01-01", null, "OneWay", 1,
                "Economy");

        // Create an empty Flight Option list.
        List<FlightOption> expectedFlights = Arrays.asList(new FlightOption());

        // Mock the repository call - Establishes the expected behavior of the
        // repository.
        when(flightRepository.findByDepartureCityIgnoreCaseAndArriveCityIgnoreCase(anyString(), anyString()))
                .thenReturn(expectedFlights);

        // Call to the service method, pass the request, return the expected list of
        // flights.
        List<FlightOption> actualFlights = flightSearchService.searchFlights(request);

        // Assert that the expected and actual lists are equal.
        assertEquals(expectedFlights, actualFlights);

        // Verify that the repository was called with the correct arguments.
        verify(flightRepository).findByDepartureCityIgnoreCaseAndArriveCityIgnoreCase("Boston", "London");
    }

}
