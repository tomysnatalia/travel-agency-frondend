package com.travel.agency.frontend.backend.hotel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreationHotelDto {

    @JsonProperty("hotelName")
    private String hotelName;

    @JsonProperty("locationCountry")
    private String locationCountry;

    @JsonProperty("locationCity")
    private String locationCity;

    @JsonProperty("closerAirport")
    private String closerAirport;

    @JsonProperty("hotelOfficialRating")
    private String hotelOfficialRating;

    @JsonProperty("pricePerNightForAdult")
    private Long pricePerNightForAdult;

    @JsonProperty("pricePerNightForKid")
    private Long pricePerNightForKid;

    @JsonProperty("foodOption")
    private String foodOption;

    @JsonProperty("duration")
    private Long duration;

    @Override
    public String toString() {
        return "CreationHotelDto{" +
                "hotelName='" + hotelName + '\'' +
                ", locationCountry='" + locationCountry + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", closerAirport='" + closerAirport + '\'' +
                ", hotelOfficialRating='" + hotelOfficialRating + '\'' +
                ", pricePerNightForAdult=" + pricePerNightForAdult +
                ", pricePerNightForKid=" + pricePerNightForKid +
                ", foodOption='" + foodOption + '\'' +
                ", duration=" + duration +
                '}';
    }
}
