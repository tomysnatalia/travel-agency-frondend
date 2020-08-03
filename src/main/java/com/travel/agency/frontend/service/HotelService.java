package com.travel.agency.frontend.service;

import com.travel.agency.frontend.domain.hotel.DurationOption;
import com.travel.agency.frontend.domain.hotel.FoodOption;
import com.travel.agency.frontend.domain.hotel.Hotel;
import com.travel.agency.frontend.domain.hotel.HotelRating;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class HotelService {

    private Set<Hotel> hotels;
    private static  HotelService hotelService;

    private HotelService() {
        this.hotels = exampleData();
    }

    public static HotelService getInstance() {
        if (hotelService == null) {
            hotelService = new HotelService();
        }
        return hotelService;
    }

    public Set getHotel() {
        return new HashSet<Hotel>(hotels);
    }

    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
    }

    public static Set<Hotel> exampleData() {
        Set<Hotel> hotels = new HashSet<>();
        hotels.add(new Hotel("Room Mate Larios", "Italy", "Roma", "Roma", HotelRating.three, 520, 170, FoodOption.BB, DurationOption.week));
        hotels.add(new Hotel("Azuuro Beach", "Spain", "Malaga", "Malaga", HotelRating.four, 450, 150, FoodOption.HB, DurationOption.week));
        hotels.add(new Hotel("La Moraga de PonienteB", "Greece", "Leptokaria", "Athens", HotelRating.five, 520, 170, FoodOption.AllInclusive, DurationOption.twoWeeks));
        return hotels;
    }

    public Set findByHotelName(String hotelName) {
        return hotels.stream().filter(hotel -> hotel.getHotelName().contains(hotelName)).collect(Collectors.toSet());
    }

    public Set findByLocationCity(String locationCity) {
        return hotels.stream().filter(hotel -> hotel.getLocationCity().contains(locationCity)).collect(Collectors.toSet());
    }

    public Set findByLocationCountry(String locationCountry) {
        return hotels.stream().filter(hotel -> hotel.getLocationCountry().contains(locationCountry)).collect(Collectors.toSet());
    }

    public Set findByCloserAirport(String closerAirport) {
        return hotels.stream().filter(hotel -> hotel.getCloserAirport().contains(closerAirport)).collect(Collectors.toSet());
    }

    public Set findByHotelOfficialRating (String hotelOfficialRating) {
        HotelRating hotelRating = HotelRating.valueOf(hotelOfficialRating);
        return hotels.stream()
                .filter(hotel -> hotel.getHotelOfficialRating() == hotelRating)
                .collect(Collectors.toSet());
    }

    public Set findByFoodOption(String foodOption) {
        FoodOption food =  FoodOption.valueOf(foodOption);
        return hotels.stream()
                .filter(hotel -> hotel.getFoodOption() == food)
                .collect(Collectors.toSet());
    }

    public Set findByDuration (String duration) {
        DurationOption durationOption = DurationOption.valueOf(duration);
        return hotels.stream()
                .filter(hotel -> hotel.getDuration() == durationOption)
                .collect(Collectors.toSet());
    }
}
