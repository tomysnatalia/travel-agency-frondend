package com.travel.agency.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    private String departure;
    private String arrival;
    private LocalDate departureDate;
    private LocalDate returnDate;
    int price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return departure.equals(flight.departure) &&
                arrival.equals(flight.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, arrival);
    }


    public void setFlightDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnFlightDate(LocalDate returnFlightDate) {
        this.returnDate = returnFlightDate;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
