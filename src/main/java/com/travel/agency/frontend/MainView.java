package com.travel.agency.frontend;

import com.travel.agency.frontend.domain.Flight;
import com.travel.agency.frontend.domain.hotel.Hotel;
import com.travel.agency.frontend.domain.Reservation;
import com.travel.agency.frontend.service.FlightService;
import com.travel.agency.frontend.service.HotelService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private FlightService flightService = FlightService.getInstance();
    private HotelService hotelService = HotelService.getInstance();


    private Button buttonFlight = new Button("Flights");
    private Button buttonHotels = new Button("Hotels");
    private Button buttonReservation = new Button("Reservation");


    private Grid gridFlight = new Grid<>(Flight.class);
    private Grid hotelGrid= new Grid<>(Hotel.class);
    private Grid gridReservation = new Grid<>(Reservation.class);

    private TextField filterDeparture = new TextField();
    private TextField filterArrival = new TextField();

    private FlightForm flightForm = new FlightForm(this);
    private HotelForm hotelForm = new HotelForm(this);

    public MainView() {
        buttonFlight.addClickListener(e -> {

            filterDeparture.setPlaceholder("Filter by departure");
            filterDeparture.setClearButtonVisible(true);
            filterDeparture.setValueChangeMode(ValueChangeMode.EAGER);
            filterDeparture.addValueChangeListener(f -> updateDeparture());

            filterArrival.setPlaceholder("Filter by arrival");
            filterArrival.setClearButtonVisible(true);
            filterArrival.setValueChangeMode(ValueChangeMode.EAGER);
            filterArrival.addValueChangeListener(f -> updateArrival());

            HorizontalLayout mainContent = new HorizontalLayout(filterDeparture, filterArrival);
            add(mainContent);
            gridFlight.setColumns("departure", "arrival", "departureDate", "returnDate", "price");
            add(gridFlight);
            setSizeFull();
            refreshFlight();
            gridFlight.asSingleSelect().clear();
            flightForm.setFlight(new Flight());
        });

        buttonHotels.addClickListener(e -> {
            add(hotelForm);
            hotelGrid.asSingleSelect().clear();
        });

        buttonReservation.addClickListener(e ->
                gridReservation.asSingleSelect().clear());

        HorizontalLayout toolBar = new HorizontalLayout(buttonFlight, buttonHotels, buttonReservation);
        add(toolBar);
        setSizeFull();
    }

    public void refreshFlight() {
        gridFlight.setItems(flightService.getFlight());
    }


    private void updateDeparture()  {
        gridFlight.setItems(flightService.findByDeparture(filterDeparture.getValue()));
    }

    private void updateArrival()  {
        gridFlight.setItems(flightService.findByArrival(filterArrival.getValue()));
    }
}





