package com.travel.agency.frontend.domain.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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
