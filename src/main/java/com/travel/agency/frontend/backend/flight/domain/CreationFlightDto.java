package com.travel.agency.frontend.backend.flight.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class CreationFlightDto {
    @JsonProperty("departure")
    private String departure;

    @JsonProperty("arrival")
    private String arrival;

    @JsonProperty("departureDate")
    private LocalDate departureDate;

    @JsonProperty("returnDate")
    private LocalDate returnDate;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("flightNumber")
    private String flightNumber;

    @Override
    public String toString() {
        return "CreationFlightDto{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", price=" + price +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }
}
