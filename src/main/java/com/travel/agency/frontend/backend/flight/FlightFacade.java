package com.travel.agency.frontend.backend.flight;

import com.travel.agency.frontend.backend.flight.domain.FlightDto;
import com.travel.agency.frontend.backend.flight.mapper.FlightMapper;
import com.travel.agency.frontend.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightFacade {
    @Autowired
    private FlightMapper flightMapper;
    @Autowired
    private FlightClient flightClient;

    public List<Flight> getAllFlights() { return flightMapper.mapToFlightListFromFlightListDto(flightClient.getFlights()); }

    public Integer createFlight(final Flight flight) { return flightClient.createFlight(flightMapper.mapToCreationFlightDto(flight)); }

    public Flight updateFlight(final Flight flight) { return flightMapper.mapToFlight(flightClient.updateFlight(flightMapper.mapToFlightDto(flight))); }

    public void deleteFlight(final Long id) {
        flightClient.deleteFlight(id);
    }

    public Flight findFlightById(final Long id) { return flightMapper.mapToFlight(flightClient.getFlightById(id)); }

    public List<Flight> findByDeparture(final String departure) { return flightMapper.mapToFlightListFromFlightListDto(flightClient.getFlightByDeparture(departure)); }

    public List<Flight> findByArrival(final String arrival) { return flightMapper.mapToFlightListFromFlightListDto(flightClient.getFlightByArrival(arrival)); }

    public List<Flight> findByFlightNumber(final String flightNumber) { return flightMapper.mapToFlightListFromFlightListDto(flightClient.getFlightByFlightNumber(flightNumber)); }

    public List<Flight> findByDepartureDate(final String departureDate) { return flightMapper.mapToFlightListFromFlightListDto(flightClient.getFlightByDepartureDate(departureDate)); }

    public List<Flight> findByReturnDate(final String returnDate) { return flightMapper.mapToFlightListFromFlightListDto(flightClient.getFlightByReturnDate(returnDate)); }
}
