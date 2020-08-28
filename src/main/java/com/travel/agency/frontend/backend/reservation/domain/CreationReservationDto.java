package com.travel.agency.frontend.backend.reservation.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreationReservationDto {

    @JsonProperty("flight")
    private Long flightId;

    @JsonProperty("hotelId")
    private Long hotelId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("numberOfAdults")
    private Long numberOfAdults;

    @JsonProperty("numberOfKids")
    private Long numberOfKids;

    @JsonProperty("hotelPrice")
    private Long hotelPrice;

    @JsonProperty("deposit")
    private Long deposit;

    @JsonProperty("paymentStatus")
    private String  paymentStatus;

    @JsonProperty("paymentDepositStatus")
    private String paymentDepositStatus;

    @JsonProperty("paymentDate")
    private LocalDate paymentDate;

    @JsonProperty("hotelPriceWithFlight")
    private Long hotelPriceWithFlight;

    @Override
    public String toString() {
        return "CreationReservationDto{" +
                "flightId=" + flightId +
                ", hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfKids=" + numberOfKids +
                ", hotelPrice=" + hotelPrice +
                ", deposit=" + deposit +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentDepositStatus='" + paymentDepositStatus + '\'' +
                ", paymentDate=" + paymentDate +
                ", hotelPriceWithFlight=" + hotelPriceWithFlight +
                '}';
    }
}
