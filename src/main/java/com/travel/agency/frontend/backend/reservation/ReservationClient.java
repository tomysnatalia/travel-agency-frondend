package com.travel.agency.frontend.backend.reservation;

import com.travel.agency.frontend.backend.flight.domain.FlightDto;
import com.travel.agency.frontend.backend.hotel.domain.CreationHotelDto;
import com.travel.agency.frontend.backend.reservation.domain.CreationReservationDto;
import com.travel.agency.frontend.backend.reservation.domain.ReservationDto;
import com.travel.agency.frontend.config.AdminConfig;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ReservationClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationClient.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AdminConfig adminConfig;

    public List<ReservationDto> getReservations() {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/reservations")
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            ReservationDto[] response = restTemplate.getForObject(url, ReservationDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public Integer createReservation(final CreationReservationDto creationReservationDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreationReservationDto> request = new HttpEntity<>(creationReservationDto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/reservation")
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            return restTemplate.exchange(url, HttpMethod.POST, request, Integer.class).getStatusCodeValue();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }
    }

    public ReservationDto updateReservation(final ReservationDto reservationDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ReservationDto> request = new HttpEntity<>(reservationDto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/reservation")
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            return restTemplate.exchange(url, HttpMethod.PUT, request, ReservationDto.class).getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new ReservationDto();
        }
    }

    public void deleteReservation(final Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/reservation/" + id)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
    }


    public ReservationDto getReservationById(final Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/reservation/" + id)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            ReservationDto response = restTemplate.getForObject(url, ReservationDto.class);
            return response;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ReservationDto();
        }
    }

    public List<ReservationDto> getReservationBySurname(final String surname) {
        URI url =  UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/reservation/" + surname)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            ReservationDto[] response = restTemplate.getForObject(url, ReservationDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<ReservationDto> getReservationByPaymentDate(final LocalDate paymentDate) {
        URI url =  UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/reservation/" + paymentDate)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            ReservationDto[] response = restTemplate.getForObject(url, ReservationDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }


}



