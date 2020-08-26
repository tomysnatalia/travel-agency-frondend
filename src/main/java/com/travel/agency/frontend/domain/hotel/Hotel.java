package com.travel.agency.frontend.domain.hotel;

import com.vaadin.flow.data.converter.StringToIntegerConverter;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private String id;
    private String hotelName;
    private String locationCountry;
    private String locationCity;
    private String closerAirport;
    private String hotelOfficialRating;

    private String pricePerNightForAdult;
    private String pricePerNightForKid;
    private String foodOption;
    private String duration;

    public void setId(String id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public void setCloserAirport(String closerAirport) {
        this.closerAirport = closerAirport;
    }

    public void setHotelOfficialRating(String hotelOfficialRating) {
        this.hotelOfficialRating = hotelOfficialRating;
    }

    public void setPricePerNightForAdult(String pricePerNightForAdult) {
        this.pricePerNightForAdult = pricePerNightForAdult;
    }

    public void setPricePerNightForKid(String pricePerNightForKid) {
        this.pricePerNightForKid = pricePerNightForKid;
    }

    public void setFoodOption(String foodOption) {
        this.foodOption = foodOption;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return hotelName.equals(hotel.hotelName) &&
                locationCountry.equals(hotel.locationCountry) &&
                locationCity.equals(hotel.locationCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelName, locationCountry, locationCity);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", locationCountry='" + locationCountry + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", closerAirport='" + closerAirport + '\'' +
                ", hotelOfficialRating=" + hotelOfficialRating +
                ", pricePerNightForAdult=" + pricePerNightForAdult +
                ", pricePerNightForKid=" + pricePerNightForKid +
                ", foodOption=" + foodOption +
                ", duration=" + duration +
                '}';
    }
}
