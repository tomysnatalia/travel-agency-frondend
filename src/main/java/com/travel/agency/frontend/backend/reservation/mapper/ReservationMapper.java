package com.travel.agency.frontend.backend.reservation.mapper;

import com.travel.agency.frontend.backend.reservation.domain.ReservationDto;
import com.travel.agency.frontend.domain.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public List<Reservation> mapToReservationListFromReservationListDto( List<ReservationDto> reservationDtoList) {
        return mapToReservationList(reservationDtoList);
    }

    public Reservation mapToReservation (ReservationDto reservationDto) {
        return new Reservation(
                (String.valueOf(reservationDto.getId())),
                (String.valueOf(reservationDto.getFlightId())),
                (String.valueOf(reservationDto.getHotelId())),
                reservationDto.getName(),
                reservationDto.getSurname(),
                reservationDto.getEmail(),
                reservationDto.getPhoneNumber(),
                (String.valueOf(reservationDto.getNumberOfAdults())),
                (String.valueOf(reservationDto.getNumberOfKids())),
                (String.valueOf(reservationDto.getHotelPrice())),
                (String.valueOf(reservationDto.getDeposit())),
                reservationDto.getPaymentStatus(),
                reservationDto.getPaymentDepositStatus(),
                (String.valueOf(reservationDto.getPaymentDate())),
                (String.valueOf(reservationDto.getHotelPriceWithFlight())));
    }

    public ReservationDto mapToReservationDto (Reservation reservation) {
        return new ReservationDto(
                (Long.valueOf(reservation.getId())),
                (Long.valueOf(reservation.getFlightId())),
                (Long.valueOf(reservation.getHotelId())),
                reservation.getName(),
                reservation.getSurname(),
                reservation.getEmail(),
                reservation.getPhoneNumber(),
                (Long.valueOf(reservation.getNumberOfAdults())),
                (Long.valueOf(reservation.getNumberOfKids())),
                (Long.valueOf(reservation.getHotelPrice())),
                (Long.valueOf(reservation.getDeposit())),
                reservation.getPaymentStatus(),
                reservation.getPaymentDepositStatus(),
                (LocalDate.parse(reservation.getPaymentDate())),
                (Long.valueOf(reservation.getHotelPriceWithFlight())));
    }

    public List<Reservation> mapToReservationList(List<ReservationDto> reservationDtoList) {
        return reservationDtoList.stream()
                .distinct()
                .map(this::mapToReservation)
                .collect(Collectors.toList());
    }
}
