package com.travel.agency.frontend.backend.hotel;

import com.travel.agency.frontend.backend.hotel.mapper.HotelMapper;
import com.travel.agency.frontend.domain.hotel.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelFacade {
    @Autowired
    private  HotelClient hotelClient;
    @Autowired
    private  HotelMapper hotelMapper;

    public List<Hotel> getAllHotels() {
        return hotelMapper.mapToHotelListFromHotelListDto(hotelClient.getHotels());
    }

    public Integer createHotel(final Hotel hotel) { return hotelClient.createHotel(hotelMapper.mapToCreationHotelDto(hotel)); }

    public Hotel updateHotel(final Hotel hotel) { return hotelMapper.mapToHotel(hotelClient.updateHotel(hotelMapper.mapToHotelDto(hotel))); }

    public void deleteHotel(final Long id) { hotelClient.deleteHotel(id); }

    public List<Hotel> findByHotelName(final String hotelName) { return hotelMapper.mapToHotelListFromHotelListDto(hotelClient.getHotelByName(hotelName)); }

    public List<Hotel> findByLocationCity(final String locationCity) { return hotelMapper.mapToHotelListFromHotelListDto(hotelClient.getHotelByCity(locationCity)); }

    public List<Hotel> findByLocationCountry(final String locationCountry) { return hotelMapper.mapToHotelListFromHotelListDto(hotelClient.getHotelByCountry(locationCountry)); }

    public List<Hotel> findByHotelOfficialRating(final String hotelOfficialRating) { return hotelMapper.mapToHotelListFromHotelListDto(hotelClient.getHotelByRating(hotelOfficialRating)); }

    public List<Hotel> findByFoodOption(final String foodOption) { return hotelMapper.mapToHotelListFromHotelListDto(hotelClient.getHotelByFoodOption(foodOption)); }


}
