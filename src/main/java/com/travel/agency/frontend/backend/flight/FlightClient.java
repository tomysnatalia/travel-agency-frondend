package com.travel.agency.frontend.backend.flight;

import com.travel.agency.frontend.backend.flight.domain.CreationFlightDto;
import com.travel.agency.frontend.backend.flight.domain.FlightDto;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FlightClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightClient.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AdminConfig adminConfig;

    public List<FlightDto> getFlights() {

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/flights")
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            FlightDto[] response = restTemplate.getForObject(url, FlightDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public Integer createFlight(final CreationFlightDto creationFlightDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreationFlightDto> request = new HttpEntity<>(creationFlightDto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/flight")
                .build().encode().toUri();
        LOGGER.info("url: " + url);
        try {
             return restTemplate.exchange(url, HttpMethod.POST, request, Integer.class). getStatusCodeValue();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }
    }

    public FlightDto updateFlight(final FlightDto flightDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FlightDto> request = new HttpEntity<>(flightDto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/flight")
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            return restTemplate.exchange(url, HttpMethod.PUT, request, FlightDto.class).getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new FlightDto();
        }
    }

    public void deleteFlight(final Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/flight/" + id)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public FlightDto getFlightById(final Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/flightId/" + id)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            FlightDto response = restTemplate.getForObject(url, FlightDto.class);
            return (response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new FlightDto();
        }
    }

    public List<FlightDto> getFlightByDeparture(final String departure) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/departure/" + departure)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            FlightDto[] response = restTemplate.getForObject(url, FlightDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<FlightDto> getFlightByArrival(final String arrival) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/arrival/" + arrival)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            FlightDto[] response = restTemplate.getForObject(url, FlightDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<FlightDto> getFlightByFlightNumber(final String flightNumber) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/flightNumber/" + flightNumber)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            FlightDto[] response = restTemplate.getForObject(url, FlightDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<FlightDto> getFlightByDepartureDate(final String departureDate) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/departureDate/" + departureDate)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            FlightDto[] response = restTemplate.getForObject(url, FlightDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }

    public List<FlightDto> getFlightByReturnDate(final String returnDate) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/v3/returnDate/" + returnDate)
                .build().encode().toUri();
        LOGGER.info("url: " + url);

        try {
            FlightDto[] response = restTemplate.getForObject(url, FlightDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }
}


