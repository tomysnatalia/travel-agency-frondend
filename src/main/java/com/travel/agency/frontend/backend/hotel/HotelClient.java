package com.travel.agency.frontend.backend.hotel;

import com.travel.agency.frontend.backend.flight.domain.CreationFlightDto;
import com.travel.agency.frontend.backend.flight.domain.FlightDto;
import com.travel.agency.frontend.backend.hotel.domain.CreationHotelDto;
import com.travel.agency.frontend.backend.hotel.domain.HotelDto;
import com.travel.agency.frontend.config.AdminConfig;

import com.travel.agency.frontend.domain.hotel.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class HotelClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelClient.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AdminConfig adminConfig;

    public List<HotelDto> getHotels() {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotels")
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            HotelDto[] response = restTemplate.getForObject(url, HotelDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public Integer createHotel(final CreationHotelDto creationHotelDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreationHotelDto> request = new HttpEntity<>(creationHotelDto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotel")
                .build().encode().toUri();
        LOGGER.info("url: " + url);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, request, Integer.class).getStatusCodeValue();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }
    }

    public HotelDto updateHotel(final HotelDto hotelDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HotelDto> request = new HttpEntity<>(hotelDto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotel")
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            return restTemplate.exchange(url, HttpMethod.PUT, request, HotelDto.class).getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new HotelDto();
        }
    }

    public void deleteHotel(final Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotel/" + id)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<HotelDto> getHotelByName(final String hotelName) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotelName/" + hotelName)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            HotelDto[] response = restTemplate.getForObject(url, HotelDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<HotelDto> getHotelByCity(final String locationCity) {
        URI url =  UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotelCity/" + locationCity)
                .build().encode().toUri();
        try {
            HotelDto[] response = restTemplate.getForObject(url, HotelDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<HotelDto> getHotelByCountry(final String locationCountry) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotelCountry/" + locationCountry)
                .build().encode().toUri();

        try {
            HotelDto[] response = restTemplate.getForObject(url, HotelDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<HotelDto> getHotelByRating(final String hotelOfficialRating) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotelRating/" + hotelOfficialRating)
                .build().encode().toUri();
        try {
            HotelDto[] response = restTemplate.getForObject(url, HotelDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<HotelDto> getHotelByFoodOption(final String foodOption) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/hotelFood/" + foodOption)
                .build().encode().toUri();

        try {
            HotelDto[] response = restTemplate.getForObject(url, HotelDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error((e.getMessage()));

            return new ArrayList<>();
        }
    }
}
