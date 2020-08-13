package com.travel.agency.frontend.service;

import com.travel.agency.frontend.domain.Flight;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightService {


    private Set<Flight> flights;
    private static FlightService flightService;
    private ReservationService reservationService;

    private FlightService() {
        this.flights = exampleData();
    }

    public static FlightService getInstance() {
        if (flightService == null) {
            flightService = new FlightService();
        }
        return flightService;
    }

    public Set getFlight() {
        return new HashSet<Flight>(flights);
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    private Set<Flight> exampleData() {
        Set<Flight> flights = new HashSet<>();
        flights.add(new Flight("75","Warsaw", "Alicante", LocalDate.of(2020,7,31), LocalDate.of(2020,8,7), "580"));
        //flights.add(new Flight( "76","Warsaw", "Malaga","2020-09-05", "2020-09-13", "450"));
       // flights.add(new Flight("78","Katowice", "Faro", "2020-08-02", "2020-8-10", "450"));
        return flights;
    }

    public Set findByDeparture(String departure) {
        return flights.stream().filter(flight -> flight.getDeparture().contains(departure)).collect(Collectors.toSet());
    }

    public Set findByArrival(String arrival) {
        return flights.stream().filter(flight -> flight.getArrival().contains(arrival)).collect(Collectors.toSet());
    }


    public void save(Flight flight) {
     this.flights.add(flight);

    }
}

