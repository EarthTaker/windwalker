package edu.snhu.garrison.windwalker.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import edu.snhu.garrison.windwalker.Model.FlightOption;
import edu.snhu.garrison.windwalker.Model.FlightSearchRequest;
import edu.snhu.garrison.windwalker.Repository.FlightRepository;

//Establishes FlightSearchService as a Spring Service component (Singleton Design Pattern)
/**
 * Service responsible for handling flight search logic
 * and delegating data access to the repository layer.
 */
@Service
public class FlightSearchService {

    private final FlightRepository flightRepository;

    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;

    }

    /**
     * Searches for flights matching the criteria specified in the search request.
     * 
     * This method acts as a facade, taking a complex request object, extracting
     * the necessary parameters (From/To cities), and delegating the actual
     * query execution to the Repository layer.
     * 
     * @param searchRequest The DTO containing user search criteria (Origin,
     *                      Destination, Dates, etc.)
     * @return A List of FlightOption objects that match the route. Returns empty
     *         list if no matches found.
     */
    public List<FlightOption> searchFlights(FlightSearchRequest searchRequest) {

        List<FlightOption> flights = flightRepository
                .findByDepartureCityIgnoreCaseAndArriveCityIgnoreCase(searchRequest.getFrom(), searchRequest.getTo());

        return flights;
    }
}
