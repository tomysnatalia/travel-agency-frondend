package com.travel.agency.frontend.form;

import com.travel.agency.frontend.MainView;
import com.travel.agency.frontend.domain.Flight;
import com.travel.agency.frontend.domain.Reservation;
import com.travel.agency.frontend.service.FlightService;
import com.travel.agency.frontend.service.ReservationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class FlightForm extends FormLayout {


    private ReservationService reservationService = ReservationService.getInstance();
    private FlightService flightService = FlightService.getInstance();
    private Grid gridFlight = new Grid<>(Flight.class);

    private TextField flightId = new TextField("flight number");
    private TextField departure = new TextField("departure");
    private TextField arrival = new TextField("arrival");
    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField phoneNumber = new TextField("phone number");
    private TextField numberOfAdults = new TextField("adults");
    private TextField numberOfKids= new TextField("kids");
    private DatePicker departureDate = new DatePicker("departure date");
    private DatePicker returnDate = new DatePicker("return date");

    private MainView mainView;

    private Button makeReservation = new Button("make reservation");
    private Button goBack = new Button("go back");

    Binder<Flight> binder = new Binder<>(Flight.class);

    public FlightForm(MainView mainView) {
        this.mainView = mainView;

        HorizontalLayout buttons = new HorizontalLayout(makeReservation, goBack);
        add(flightId, departure, arrival, name, surname, email, phoneNumber, numberOfAdults, numberOfKids, departureDate, returnDate, buttons);
        binder.bindInstanceFields(this);

        makeReservation.addClickListener(event -> getFlightToReservation());
        goBack.addClickListener(event -> back());
    }

    private void  getFlightToReservation() {
        Reservation reservation1 = new Reservation("56", "75", null, null, null, null, null, null, null, null,null,"false", "false", "2020-09-02",null);
        reservationService.save(reservation1);
        setFlight(null);
    }

    public void setFlight(Flight flight) {
        binder.setBean(flight);
        if (flight == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public void back() {
        setFlight(null);
    }

}