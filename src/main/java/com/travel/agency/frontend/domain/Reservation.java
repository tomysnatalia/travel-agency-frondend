package com.travel.agency.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private String id = "";
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

    public boolean isSafeToUpdate() {
        return !id.isEmpty() &&
                this.alwaysRequiredFieldsAreFilled();
    }

    public boolean isSafeToSave() {
        return id.isEmpty() && this.alwaysRequiredFieldsAreFilled();
    }

    private boolean alwaysRequiredFieldsAreFilled() {
        Pattern emailPattern = Pattern.compile(".{3,}@.{2,}\\..{2,3}");
        Pattern pricePattern = Pattern.compile("[0-9]+([.][0-9]{1,2})?");
        return !(name.isEmpty() |
                surname.isEmpty() |
                !emailPattern.matcher(email).matches() |
                !pricePattern.matcher(hotelPrice).matches()  );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id.equals(that.id) &&
                flightId.equals(that.flightId) &&
                hotelId.equals(that.hotelId);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNumberOfAdults(String numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public void setNumberOfKids(String numberOfKids) {
        this.numberOfKids = numberOfKids;
    }

    public void setHotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentDepositStatus(String paymentDepositStatus) { this.paymentDepositStatus = paymentDepositStatus; }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setHotelPriceWithFlight(String hotelPriceWithFlight) { this.hotelPriceWithFlight = hotelPriceWithFlight; }

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
