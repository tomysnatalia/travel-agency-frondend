package com.travel.agency.frontend.backend.flight.mapper;

import com.travel.agency.frontend.backend.flight.domain.CreationFlightDto;
import com.travel.agency.frontend.backend.flight.domain.FlightDto;
import com.travel.agency.frontend.domain.Flight;
import org.springframework.stereotype.Component;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper {

   public List<Flight> mapToFlightListFromFlightListDto (List<FlightDto> flightDtoList){
       return mapToFlightList(flightDtoList);
   }

    public Flight mapToFlight (final FlightDto flightDto){
        return new Flight(
                flightDto.getId().toString(),
                flightDto.getDeparture(),
                flightDto.getArrival(),
                flightDto.getDepartureDate(),
                flightDto.getReturnDate(),
                flightDto.getPrice().toString(),
                flightDto.getFlightNumber());
    }

    public FlightDto mapToFlightDto (final Flight flight) {
        return new FlightDto(
                (Long.parseLong(flight.getId())),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getDepartureDate(),
                flight.getReturnDate(),
                (Long.parseLong(flight.getPrice())),
                flight.getFlightNumber());
    }

    public List<Flight> mapToFlightList(List<FlightDto> flightDtoList) {
       return flightDtoList.stream()
                .map(this::mapToFlight)
                .distinct()
                .collect(Collectors.toList());
   }

    public List<FlightDto> mapToFlightDtoList(final List<FlightDto> flightsList) {
        return flightsList.stream()
                .map(flight -> new FlightDto (flight.getId(), flight.getDeparture(), flight.getArrival(), flight.getDepartureDate(), flight.getReturnDate(), flight.getPrice(), flight.getFlightNumber()))
                .distinct()
                .collect(Collectors.toList());
    }

    public CreationFlightDto mapToCreationFlightDto (Flight flight) {
       return new CreationFlightDto(
               flight.getDeparture(),
               flight.getArrival(),
               flight.getDepartureDate(),
               flight.getReturnDate(),
               (Long.parseLong(flight.getPrice())),
               flight.getFlightNumber());
    }
}
