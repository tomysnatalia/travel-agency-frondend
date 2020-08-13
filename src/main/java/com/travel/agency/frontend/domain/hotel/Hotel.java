package com.travel.agency.frontend.domain.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private String hotelId;
    private String hotelName;
    private String locationCountry;
    private String locationCity;
    private String closerAirport;
    private HotelRating hotelOfficialRating;

    private int pricePerNightForAdult;
    private int pricePerNightForKid;
    private FoodOption foodOption;
    private DurationOption duration;

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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

    public void setHotelOfficialRating(HotelRating hotelOfficialRating) {
        this.hotelOfficialRating = hotelOfficialRating;
    }

    public void setPricePerNightForAdult(int pricePerNightForAdult) {
        this.pricePerNightForAdult = pricePerNightForAdult;
    }

    public void setPricePerNightForKid(int pricePerNightForKid) {
        this.pricePerNightForKid = pricePerNightForKid;
    }

    public void setFoodOption(FoodOption foodOption) {
        this.foodOption = foodOption;
    }

    public void setDuration(DurationOption duration) {
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
}
