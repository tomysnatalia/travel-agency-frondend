package com.travel.agency.frontend.form;

import com.travel.agency.frontend.MainView;
import com.travel.agency.frontend.domain.Reservation;
import com.travel.agency.frontend.service.ReservationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.Set;


public class ReservationForm extends FormLayout {

    private Set<Reservation> reservations;
    private MainView mainView;
    private ReservationService reservationService = ReservationService.getInstance();
    private Grid reservationGrid = new Grid<>(Reservation.class);

    private TextField reservationId = new TextField("reservationId");
    private TextField flightId= new TextField("flightId");
    private TextField hotelId = new TextField ("hotelId");
    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField phoneNumber = new TextField("phoneNumber");
    private TextField numberOfAdults = new TextField("numberOfAdults");
    private TextField numberOfKids= new TextField("numberOfKids");


    Button update = new Button("Update information");
    Button deleteReservation = new Button("delete reservation");

    Binder<Reservation> binder = new Binder<>(Reservation.class);

    public ReservationForm(MainView mainView) {
        this.mainView = mainView;

        HorizontalLayout buttons = new HorizontalLayout(update, deleteReservation);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(reservationId, flightId, hotelId, name, surname, email, phoneNumber, numberOfAdults, numberOfKids, buttons);
        binder.bindInstanceFields(this);

        update.addClickListener(event -> update());
        deleteReservation.addClickListener(event -> deleteReservation());
    }

    public void setReservation(Reservation reservation) {
        binder.setBean(reservation);
        if (reservation == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

    public void update() {
        Reservation reservation = binder.getBean();
        reservationService.save(reservation);
        setReservation(null);

    }

    public void deleteReservation() {
        Reservation reservation = binder.getBean();
        reservationService.delete(reservation);
        refresh();
        setReservation(null);
    }

    public void refresh() {
        reservationGrid.setItems(reservationService.getReservations());
    }

    public void save() {
        Reservation reservation = binder.getBean();
        reservations.add(reservation);
    }


}