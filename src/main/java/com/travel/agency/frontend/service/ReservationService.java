package com.travel.agency.frontend.service;

import com.travel.agency.frontend.domain.Reservation;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class ReservationService {

    private Set<Reservation> reservations;

    private static  ReservationService reservationService;

    private ReservationService() {
        this.reservations = exampleData();
    }

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public Set getReservations() {
        return new HashSet<>(reservations);
    }

    public Set getReservation(String reservationId) {
        return getReservation(reservationId);
    }

    private Set exampleData() {
        Set reservations = new HashSet<>();
        reservations.add(new Reservation("55", "3", "47", "John", "Smith", "john.s@gmail.com", "520369541", "2", "0", null,"1500","false", "true", "2020 -8- 20","0"));
        return reservations;
    }

    public Set findById(String reservationId) {
        return reservations.stream().filter(reservation -> reservation.getReservationId().equals(reservationId)).collect(Collectors.toSet());
    }

    public void save(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void delete(Reservation reservation) {
        this.reservations.remove(reservation);
    }

}


