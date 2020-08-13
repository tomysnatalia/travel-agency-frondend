package com.travel.agency.frontend.form;

import com.travel.agency.frontend.MainView;
import com.travel.agency.frontend.domain.Flight;
import com.travel.agency.frontend.domain.Reservation;
import com.travel.agency.frontend.domain.hotel.DurationOption;
import com.travel.agency.frontend.domain.hotel.FoodOption;
import com.travel.agency.frontend.domain.hotel.Hotel;
import com.travel.agency.frontend.domain.hotel.HotelRating;
import com.travel.agency.frontend.service.HotelService;
import com.travel.agency.frontend.service.ReservationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;

public class HotelForm extends VerticalLayout {

    private ReservationService reservationService;
    private HotelService hotelService = HotelService.getInstance();
    private Grid hotelGrid = new Grid<>(Hotel.class);

    private TextField hotelId = new TextField("hotelId");

    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField phoneNumber = new TextField("phoneNumber");
    private TextField numberOfAdults = new TextField("numberOfAdults");
    private TextField numberOfKids= new TextField("numberOfKids");

    private MainView mainView;

    private Button makeReservation = new Button("make reservation");
    private Button goBack = new Button ("go back");

    Binder<Hotel> binder = new Binder<>(Hotel.class);

    public HotelForm(MainView mainView) {
        this.mainView = mainView;

        HorizontalLayout buttons = new HorizontalLayout(makeReservation, goBack);
        add(hotelId, name, surname, email, phoneNumber, numberOfAdults, numberOfKids, buttons);
        binder.bindInstanceFields(this);

        makeReservation.addClickListener(event -> goToReservation());
        goBack.addClickListener(event -> back());
    }

    public void goToReservation() {
        Reservation reservation1 = new Reservation("56", null, "85", null, null, null, null, null, null, null,null,"false", "false", "2020-09-02",null);
        reservationService.save(reservation1);
        setHotel(null);
    }

    public void back() {
        setHotel(null);
    }

    public void setHotel(Hotel hotel) {
        binder.setBean(hotel);
        if (hotel == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}



