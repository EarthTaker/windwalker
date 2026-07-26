package edu.snhu.garrison.windwalker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.snhu.garrison.windwalker.Model.Booking;

/**
 * Method: Extends the functionality from JPA Repository Spring Data Interface
 * to provide common database operations generated at runtime.
 *
 * Note: When JPA Repository has been extended from, Spring derives queries
 * based on the method name provided. It does this at runtime.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
