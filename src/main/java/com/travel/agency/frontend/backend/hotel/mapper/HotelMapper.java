package com.travel.agency.frontend.backend.hotel.mapper;

import com.travel.agency.frontend.backend.hotel.domain.CreationHotelDto;
import com.travel.agency.frontend.backend.hotel.domain.HotelDto;
import com.travel.agency.frontend.domain.hotel.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    public List<Hotel> mapToHotelListFromHotelListDto(final List <HotelDto> hotelDtoList) {
        return mapToHotelList(hotelDtoList);
    }

    public Hotel mapToHotel(final HotelDto hotelDto) {
        return new Hotel(
                (String.valueOf(hotelDto.getId())),
                hotelDto.getHotelName(),
                hotelDto.getLocationCountry(),
                hotelDto.getLocationCity(),
                hotelDto.getCloserAirport(),
                hotelDto.getHotelOfficialRating(),
                (String.valueOf(hotelDto.getPricePerNightForAdult())),
                (String.valueOf(hotelDto.getPricePerNightForKid())),
                (String.valueOf(hotelDto.getFoodOption())),
                (String.valueOf(hotelDto.getDuration())));
    }

    public HotelDto mapToHotelDto (Hotel hotel){
        return new HotelDto(
                (Long.valueOf(hotel.getId())),
                hotel.getHotelName(),
                hotel.getLocationCountry(),
                hotel.getLocationCity(),
                hotel.getCloserAirport(),
                (String.valueOf(hotel.getHotelOfficialRating())),
                (Long.valueOf(hotel.getPricePerNightForAdult())),
                (Long.valueOf(hotel.getPricePerNightForKid())),
                (String.valueOf(hotel.getFoodOption())),
                (Long.valueOf(String.valueOf(hotel.getDuration()))));
    }

    public List<Hotel> mapToHotelList(List<HotelDto> hotelDtoList) {
        return hotelDtoList.stream()
                .map(this::mapToHotel)
                .distinct()
                .collect(Collectors.toList());
    }

    public CreationHotelDto mapToCreationHotelDto(Hotel hotel) {
        return new CreationHotelDto(
                hotel.getHotelName(),
                hotel.getLocationCountry(),
                hotel.getLocationCity(),
                hotel.getCloserAirport(),
                hotel.getHotelOfficialRating(),
                (Long.parseLong(hotel.getPricePerNightForAdult())),
                (Long.parseLong(hotel.getPricePerNightForKid())),
                hotel.getFoodOption(),
                (Long.parseLong(hotel.getDuration())));
    }
}
