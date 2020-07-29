package com.travel.agency.frontend.service;
import com.travel.agency.frontend.domain.Flight;

import java.util.HashSet;
import java.util.Set;

public class FlightService {

    private Set flights;
    private static FlightService flightService;

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
        return new HashSet<>(flights);
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    private Set exampleData() {
        Set flights = new HashSet<>();
        flights.add(new Flight(1L, "Warsaw", "Alicante", 850));
        flights.add(new Flight(2L, "Warsaw", "Malaga", 450));
        return flights;
    }


}


