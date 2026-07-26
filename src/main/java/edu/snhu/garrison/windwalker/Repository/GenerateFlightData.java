package edu.snhu.garrison.windwalker.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.snhu.garrison.windwalker.Model.Airline;
import edu.snhu.garrison.windwalker.Model.Airport;
import edu.snhu.garrison.windwalker.Model.FlightOption;

//Spring creates an instance of the class at startup due to the Component Annotation.
@Component
public class GenerateFlightData implements CommandLineRunner {

        @Autowired
        private FlightRepository flightRepository;

        private static final Random random = new Random();

        // Establishes the number of flights required to generate on startup.
        @Value("${flight.generator.count:50}")
        private int flightCount;

        private static final List<String> CABIN_OPTIONS = List.of(
                        "Economy",
                        "Business Class",
                        "First Class");

        private static final List<Airline> AIRLINE_OPTIONS = List.of(
                        new Airline("Delta Air Lines", "DL"),
                        new Airline("American Airlines", "AA"),
                        new Airline("United Airlines", "UA"),
                        new Airline("Southwest Airlines", "SW"),
                        new Airline("JetBlue Airways", "JB"),
                        new Airline("Alaska Airlines", "AS"),
                        new Airline("Spirit Airlines", "NK"),
                        new Airline("Frontier Airlines", "F9"),
                        new Airline("Hawaiian Airlines", "HA"));

        private static final List<Airport> AIRPORT_OPTIONS = List.of(
                        new Airport("JFK", "JFK International", "New York", "USA"),
                        new Airport("LAX", "LAX International", "Los Angeles", "USA"),
                        new Airport("ORD", "O'Hare International", "Chicago", "USA"),
                        new Airport("DEN", "Denver International", "Denver", "USA"),
                        new Airport("SFO", "San Francisco International", "San Francisco", "USA"),
                        new Airport("DFW", "Dallas/Fort Worth", "Dallas", "USA"),
                        new Airport("SEA", "Seattle-Tacoma International", "Seattle", "USA"),
                        new Airport("MIA", "Miami International", "Miami", "USA"),
                        new Airport("BOS", "Logan International", "Boston", "USA"),
                        new Airport("PHX", "Phoenix Sky Harbor", "Phoenix", "USA"));

        /**
         * Method: Automatically runs at startup.
         * If CommandLineRunner is implemented in any class, Spring Boot finds the bean
         * and calls its run method.
         * 
         * @param String... args - Ensures the method can accept zero or more String
         *                  Arguments.
         *                  Represents varargs (variable number of arguements), but
         *                  under the syntax, Java treats it like an Array[]
         */
        @Override
        public void run(String... args) throws Exception {

                List<FlightOption> flights = new ArrayList<>();

                // Test Flight
                FlightOption testFlight = new FlightOption(
                                "Delta Air Lines",
                                "JFK",
                                "DL123",
                                LocalTime.of(8, 0), // 08:00 departure
                                LocalTime.of(11, 30), // 11:30 arrival
                                210, // duration in minutes
                                349.99, // price
                                "JFK International", "New York", "USA",
                                "LAX International", "Los Angeles", "USA",
                                1, // stops
                                "Business Class");

                flights.add(testFlight);

                // Generate a list of flights to populate the H2 Database.
                for (int i = 0; i < flightCount; i++) {

                        LocalTime departureTime = randomDepartureTime();
                        int duration = randomDuration(90, 360);
                        LocalTime arrivalTime = calculateArrival(departureTime, duration);

                        Airport originPort = randomAirport();
                        Airport destinationPort = randomDifferentAirport(originPort);
                        Airline airline = randomAirline();
                        String flightNumber = airline.getCode() + (100 + random.nextInt(900));

                        FlightOption flight = new FlightOption(airline.getName(),
                                        originPort.getAirportCode(),
                                        flightNumber,
                                        departureTime,
                                        arrivalTime,
                                        duration,
                                        randomPrice(randomCabinClass()),
                                        originPort.getName(), originPort.getCity(), originPort.getCountry(),
                                        destinationPort.getName(), destinationPort.getCity(),
                                        destinationPort.getCountry(),
                                        1 + random.nextInt(4),
                                        randomCabinClass());

                        flights.add(flight);
                }

                // Adds list of flights to H2 DB.
                flightRepository.saveAll(flights);
        }

        /**
         * Method to generate a random airport from the list of airports.
         * 
         * @return An Airport Object with a Name, City, Country, and Code.
         */
        private static Airport randomAirport() {
                int index = random.nextInt(AIRPORT_OPTIONS.size());
                return AIRPORT_OPTIONS.get(index);
        }

        /**
         * Method to generate an airport that differs from the origin airport.
         * 
         * @param origin - Established as the origin airport.
         * @return - An Airport that differs from the origin airport.
         */
        private static Airport randomDifferentAirport(Airport origin) {
                Airport destination;
                do {
                        destination = randomAirport();
                } while (destination == origin);
                return destination;
        }

        /**
         * Method to generate a random Airline from the list of Airlines.
         * 
         * @return - An Airline Object with an Airline Code and Name.
         */
        private static Airline randomAirline() {
                int index = random.nextInt(AIRLINE_OPTIONS.size());
                return AIRLINE_OPTIONS.get(index);
        }

        /**
         * Method to generate a random Seat Class from the list of Seat Classes.
         * 
         * @return - A seat option.
         */
        private static String randomCabinClass() {
                int index = random.nextInt(CABIN_OPTIONS.size());
                return CABIN_OPTIONS.get(index);
        }

        /**
         * Method to generate a random departure time.
         * 
         * @return - A randomly selected departure time.
         */
        private static LocalTime randomDepartureTime() {
                int hour = 5 + random.nextInt(18); // 05:00–22:00
                int minute = random.nextInt(60);
                return LocalTime.of(hour, minute);
        }

        /**
         * Method to generate a duration.
         * 
         * @param minMinutes - Minimum amount of time it takes to fly to the
         *                   destination.
         * @param maxMinutes - Maximum amount of time it takes to fly to the
         *                   destination.
         * @return - Duration.
         */
        private static int randomDuration(int minMinutes, int maxMinutes) {
                return minMinutes + random.nextInt(maxMinutes - minMinutes + 1);
        }

        /**
         * Method to calculate the arrival time.
         * 
         * @param departure       - Establishes the starting time.
         * @param durationMinutes - Pulled from the randomly generated duration.
         * @return - Arrival Time.
         */
        private static LocalTime calculateArrival(LocalTime departure, int durationMinutes) {
                LocalTime arrival = departure.plusMinutes(durationMinutes);
                return arrival;
        }

        /**
         * Method to select a price based on a base value and its variance.
         * 
         * @param cabinClass - Derived from the list of cabin classes.
         * @return
         */
        private static double randomPrice(String cabinClass) {
                // Handle null cabin class
                if (cabinClass == null) {
                        cabinClass = "Economy";
                }
                
                // Create a base and price variance based on each cabin.
                double base = switch (cabinClass) {
                        case "First Class" -> 450;
                        case "Business Class" -> 300;
                        default -> 150;
                };
                
                double variance = switch (cabinClass) {
                        case "First Class" -> 300;
                        case "Business Class" -> 200;
                        default -> 100;
                };

                // Calculate the price of the flight - Use the rand * variance to create an
                // estimate price addition to the base value.
                double price = base + (random.nextDouble() * variance);

                // Trim the decimal places before return.
                return Math.round(price * 100.0) / 100.0;
        }

}
