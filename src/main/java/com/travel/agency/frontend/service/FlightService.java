package com.travel.agency.frontend.service;

import com.travel.agency.frontend.domain.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightService {


    private Set<Flight> flights;
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
        return new HashSet<Flight>(flights);
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    private Set<Flight> exampleData() {
        Set<Flight> flights = new HashSet<>();
        flights.add(new Flight("Warsaw", "Alicante", LocalDate.of(2020,7, 31), LocalDate.of(2020, 8, 7),580));
        flights.add(new Flight( "Warsaw", "Malaga",LocalDate.of(2020,8, 5), LocalDate.of(2020, 8, 15), 450));
        flights.add(new Flight("Katowice", "Malaga", LocalDate.of(2020,7, 31), LocalDate.of(2020, 8, 7), 450));
        flights.add(new Flight("Katowice", "Faro", LocalDate.of(2020,8, 2), LocalDate.of(2020, 8, 10), 450));
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

    public void delete(Flight flight) {
        this.flights.remove(flight);
    }


}

