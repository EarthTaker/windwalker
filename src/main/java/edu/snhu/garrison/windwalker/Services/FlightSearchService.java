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
     * Method to search for flights based on the search request.
     * 
     * @param searchRequest
     */
    public List<FlightOption> searchFlights(FlightSearchRequest searchRequest) {

        List<FlightOption> flights = flightRepository
                .findByDepartureCityIgnoreCaseAndArriveCityIgnoreCase(searchRequest.getFrom(), searchRequest.getTo());

        return flights;
    }
}
