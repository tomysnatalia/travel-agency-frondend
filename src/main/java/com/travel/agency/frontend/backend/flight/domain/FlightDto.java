package com.travel.agency.frontend.backend.flight.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDto {

    @JsonProperty("id")
    private Long id;

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
        return "FlightDto{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", price=" + price +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }
}