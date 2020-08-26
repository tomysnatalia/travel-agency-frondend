package com.travel.agency.frontend.backend.reservation;

import com.travel.agency.frontend.backend.reservation.mapper.ReservationMapper;
import com.travel.agency.frontend.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationFacade {

    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private ReservationClient reservationClient;

    public List<Reservation> getAllReservations() {
        return reservationMapper.mapToReservationListFromReservationListDto(reservationClient.getReservations());
    }
}
