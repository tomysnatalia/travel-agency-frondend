package com.travel.agency.frontend.backend.reservation;

import com.travel.agency.frontend.backend.reservation.domain.ReservationDto;
import com.travel.agency.frontend.config.AdminConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
        LOGGER.info("url" + url);

        try {
            ReservationDto[] response = restTemplate.getForObject(url, ReservationDto[].class);
            return Arrays.asList(response);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());

            return new ArrayList<>();
        }
    }
}



