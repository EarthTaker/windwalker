package edu.snhu.garrison.windwalker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.snhu.garrison.windwalker.Model.FlightOption;

/**
 * Method: Extends the functionality from JPA Repository Spring Data Interface to provide common database operations generated at runtime.
 * 
 * Note: When JPA Repository has been extended from, Spring derives queries based on the method name provided. It does this at runtime.
 */
@Repository
public interface FlightRepository extends JpaRepository<FlightOption, Long>{

    //Find the Flight by Departure City and Arrive City
    List<FlightOption> findByDepartureCityIgnoreCaseAndArriveCityIgnoreCase(String departureCity, String arriveCity);
}
