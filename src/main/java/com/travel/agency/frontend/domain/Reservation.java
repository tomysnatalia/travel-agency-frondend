package com.travel.agency.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private String id;
    private String flightId;
    private String hotelId;

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String numberOfAdults;
    private String numberOfKids;
    private String hotelPrice;
    private String deposit;
    private String paymentStatus;
    private String paymentDepositStatus;
    private String paymentDate;
    private String hotelPriceWithFlight;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id.equals(that.id) &&
                flightId.equals(that.flightId) &&
                hotelId.equals(that.hotelId);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", flightId='" + flightId + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", numberOfAdults='" + numberOfAdults + '\'' +
                ", numberOfKids='" + numberOfKids + '\'' +
                ", hotelPrice='" + hotelPrice + '\'' +
                ", deposit='" + deposit + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentDepositStatus='" + paymentDepositStatus + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", hotelPriceWithFlight='" + hotelPriceWithFlight + '\'' +
                '}';
    }
}
