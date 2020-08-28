package com.travel.agency.frontend.backend.reservation;

import com.travel.agency.frontend.backend.reservation.mapper.ReservationMapper;
import com.travel.agency.frontend.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReservationFacade {

    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private ReservationClient reservationClient;

    public List<Reservation> getAllReservations() { return reservationMapper.mapToReservationListFromReservationListDto(reservationClient.getReservations()); }

    public Integer createReservation(final Reservation reservation) { return reservationClient.createReservation(reservationMapper.mapToCreationReservationDto(reservation)); }

    public Reservation updateReservation(final Reservation reservation) { return reservationMapper.mapToReservation(reservationClient.updateReservation(reservationMapper.mapToReservationDto(reservation))); }

    public void deleteReservation(final Long id) { reservationClient.deleteReservation(id); }

    public Reservation findReservationById(final Long id) { return reservationMapper.mapToReservation(reservationClient.getReservationById(id)); }

    public List<Reservation> findReservationBySurname(final String surname) { return reservationMapper.mapToReservationList(reservationClient.getReservationBySurname(surname)); }

    public List<Reservation> findReservationByPaymentDate(final LocalDate paymentDate) { return reservationMapper.mapToReservationList(reservationClient.getReservationByPaymentDate(paymentDate));
    }
}
