package com.travel.agency.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    public String flightId;
    private String departure;
    private String arrival;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return price == flight.price &&
                flightId.equals(flight.flightId) &&
                departure.equals(flight.departure) &&
                arrival.equals(flight.arrival) &&
                departureDate.equals(flight.departureDate) &&
                returnDate.equals(flight.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, departure, arrival, departureDate, returnDate, price);
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
