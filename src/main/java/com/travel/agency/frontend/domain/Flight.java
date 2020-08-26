package com.travel.agency.frontend.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String id;
    private String departure;
    private String arrival;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String price;
    private String flightNumber;


    private boolean alwaysRequiredFieldsAreFilled() {
        Pattern pricePattern = Pattern.compile("[0-9]+([.][0-9]{1,2})?");
        return !(departure.isEmpty() | arrival.isEmpty() |
                !pricePattern.matcher(price).matches() );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return price == flight.price &&
                id.equals(flight.id) &&
                departure.equals(flight.departure) &&
                arrival.equals(flight.arrival) &&
                departureDate.equals(flight.departureDate) &&
                returnDate.equals(flight.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, arrival, departureDate, returnDate, price);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", price='" + price + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }
}
