package com.travel.agency.frontend;

import com.travel.agency.frontend.backend.flight.FlightFacade;
import com.travel.agency.frontend.backend.hotel.HotelFacade;
import com.travel.agency.frontend.backend.reservation.ReservationFacade;
import com.travel.agency.frontend.domain.Flight;
import com.travel.agency.frontend.domain.Reservation;
import com.travel.agency.frontend.domain.hotel.DurationOption;
import com.travel.agency.frontend.domain.hotel.FoodOption;
import com.travel.agency.frontend.domain.hotel.Hotel;
import com.travel.agency.frontend.domain.hotel.HotelRating;
import com.travel.agency.frontend.form.FlightForm;
import com.travel.agency.frontend.form.HotelForm;
import com.travel.agency.frontend.form.ReservationForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component
@UIScope
@Route()
public class MainView extends VerticalLayout {

    private final ReservationFacade reservationFacade;
    private ReservationForm reservationForm;
    private Grid<Reservation> reservationGrid;

    private final FlightFacade flightFacade;
    private FlightForm flightForm;
    private Grid<Flight> flightGrid;

    private final HotelFacade hotelFacade;
    private HotelForm hotelForm;
    private Grid<Hotel> hotelGrid;

    public TextField reservationNumber = new TextField("reservation number");

    private Button buttonFlight = new Button("Flights");
    private Button buttonHotels = new Button("Hotels");
    private Button buttonReservation = new Button("Reservation");

    private TextField departure = new TextField("departure");
    private TextField arrival = new TextField("arrival");
    private DatePicker departureDate = new DatePicker("departure date");
    private DatePicker returnDate = new DatePicker("return date");
    private TextField flightNumber = new TextField("flight number");

    private TextField hotelName = new TextField();
    private TextField locationCountry = new TextField();
    private TextField locationCity = new TextField();
    private ComboBox hotelOfficialRating = new ComboBox<>("Hotel Stars");
    private ComboBox foodOption = new ComboBox<>("Food option");
    private ComboBox duration = new ComboBox<>("Duration");

    private Button addFlight = new Button("Add flight");
    private Button addHotel = new Button("Add hotel");
    private Button addReservation = new Button("Add reservation");

    public MainView(ReservationFacade reservationFacade, FlightFacade flightFacade, HotelFacade hotelFacade) {
        this.reservationFacade = reservationFacade;
        this.reservationForm = new ReservationForm(this.reservationFacade, this);
        this.reservationGrid = new Grid<>(Reservation.class);

        this.flightFacade = flightFacade;
        this.flightForm = new FlightForm(this.flightFacade, this);
        this.flightGrid = new Grid<>(Flight.class);

        this.hotelFacade = hotelFacade;
        this.hotelForm = new HotelForm(this.hotelFacade, this);
        this.hotelGrid = new Grid<>(Hotel.class);

        HorizontalLayout toolBar = new HorizontalLayout(buttonFlight, buttonHotels, buttonReservation);
        add(toolBar);
        setSizeFull();

        buttonFlight.addClickListener(e -> {
            removeAll();
            HorizontalLayout toolBar2 = new HorizontalLayout(buttonFlight, buttonHotels, buttonReservation, addFlight);
            add(toolBar2);
            addFlight.addClickListener(add -> {
                flightGrid.asSingleSelect().clear();
                flightForm.setFlight(new Flight());
            });

            flightForm.setFlight(null);

            HorizontalLayout mainContent = new HorizontalLayout(departure, arrival, flightNumber, departureDate, returnDate);
            add(mainContent);
            flightGrid.setColumns("id", "departure", "arrival", "departureDate", "returnDate", "flightNumber", "price");
            add(flightGrid, flightForm);

            departure.setPlaceholder("filter by departure");
            departure.setClearButtonVisible(true);
            departure.addValueChangeListener(f -> updateDeparture());

            arrival.setPlaceholder("filter by arrival");
            arrival.setClearButtonVisible(true);
            arrival.addValueChangeListener(f -> updateArrival());

            flightNumber.setPlaceholder("filter by flight number");
            flightNumber.setClearButtonVisible(true);
            flightNumber.addValueChangeListener(f -> updateFlightNumber());

            departureDate.setPlaceholder("filter by departure date");
            departureDate.addValueChangeListener(f -> updateDepartureDate());

            returnDate.setPlaceholder("filter by return date");
            returnDate.addValueChangeListener(f -> updateReturnDate());

            flightGrid.setSizeFull();
            refreshFlight();
            add(flightForm);
            flightForm.setFlight(null);
            flightGrid.asSingleSelect().addValueChangeListener(event -> flightForm.setFlight(flightGrid.asSingleSelect().getValue()));
        });

        buttonHotels.addClickListener(e -> {
            removeAll();
            HorizontalLayout toolBar3 = new HorizontalLayout(buttonFlight, buttonHotels, buttonReservation, addHotel);
            add(toolBar3);
            hotelName.setPlaceholder("Hotel name");
            hotelName.setClearButtonVisible(true);
            hotelName.setValueChangeMode(ValueChangeMode.EAGER);
            hotelName.addValueChangeListener(h -> searchByHotel());

            locationCountry.setPlaceholder("Country");
            locationCountry.setClearButtonVisible(true);
            locationCountry.setValueChangeMode(ValueChangeMode.EAGER);
            locationCountry.addValueChangeListener(h -> searchByCountry());

            locationCity.setPlaceholder("City");
            locationCity.setClearButtonVisible(true);
            locationCity.setValueChangeMode(ValueChangeMode.EAGER);
            locationCity.addValueChangeListener(h -> searchByCity());


            hotelOfficialRating.setItems(HotelRating.values());
            hotelOfficialRating.setPlaceholder("Rating stars");
            //hotelOfficialRating.setClearButtonVisible(true);
            hotelOfficialRating.addValueChangeListener(h -> searchByRating());

            foodOption.setItems(FoodOption.values());
            foodOption.setPlaceholder("Food options");
            //foodOption.setClearButtonVisible(true);
            foodOption.addValueChangeListener(h -> searchByFoodOption());

            duration.setItems(DurationOption.values());
            duration.setPlaceholder("Duration");
            //duration.setClearButtonVisible(true);
            duration.addValueChangeListener(h -> searchByDuration());

            hotelGrid.setColumns("id", "hotelName", "locationCountry", "locationCity", "closerAirport", "hotelOfficialRating", "foodOption", "duration", "pricePerNightForAdult", "pricePerNightForKid");
            HorizontalLayout horizontalLayout = new HorizontalLayout(hotelName, locationCountry, locationCity);
            HorizontalLayout horizontalLayout2 = new HorizontalLayout(hotelOfficialRating, foodOption, duration);
            add(horizontalLayout, horizontalLayout2);
            add(hotelGrid);
            setSizeFull();
            refreshHotel();
            setSizeFull();
            add(hotelForm);
            hotelForm.setHotel(null);
            hotelGrid.asSingleSelect().addValueChangeListener(event -> hotelForm.setHotel((Hotel) hotelGrid.asSingleSelect().getValue()));
        });

        buttonReservation.addClickListener(e -> {
            removeAll();
            HorizontalLayout toolBar4 = new HorizontalLayout(buttonFlight, buttonHotels, buttonReservation, addReservation);
            add(toolBar4);
            add(reservationNumber);
            reservationGrid.setColumns("id", "flightId", "hotelId", "name", "surname", "email", "phoneNumber", "numberOfAdults", "numberOfKids", "hotelPrice", "deposit", "paymentStatus", "paymentDepositStatus", "paymentDate", "hotelPriceWithFlight");
            add(reservationGrid);
            reservationNumber.setPlaceholder("Reservation number");
            reservationNumber.setClearButtonVisible(true);
            reservationNumber.addValueChangeListener(event -> search());
            setSizeFull();
            refreshReservation();
            add(reservationForm);
            reservationForm.setReservation(null);
            reservationGrid.asSingleSelect().addValueChangeListener(event -> reservationForm.setReservation((Reservation) reservationGrid.asSingleSelect().getValue()));
        });
    }

    public void search() {
        //reservationGrid.setItems(reservationFacade.findBy(Long.valueOf(String.valueOf(reservationNumber.getValue()))));
    }

    public void updateDeparture() {
        flightGrid.setItems(flightFacade.findByDeparture(departure.getValue()));}

    public void updateArrival() {
        flightGrid.setItems(flightFacade.findByArrival(arrival.getValue()));}

    public void updateFlightNumber() {
        flightGrid.setItems(flightFacade.findByFlightNumber(flightNumber.getValue()));}

    public void updateDepartureDate() {
        flightGrid.setItems(flightFacade.findByDepartureDate(String.valueOf(departureDate.getValue()))); }

    public void updateReturnDate(){
        flightGrid.setItems(flightFacade.findByReturnDate(String.valueOf(returnDate.getValue()))); }

    public void refreshFlight() {
        flightGrid.setItems(flightFacade.getAllFlights());}

    public void refreshHotel() {
        hotelGrid.setItems(hotelFacade.getAllHotels());}

    public void refreshReservation() {
        reservationGrid.setItems(reservationFacade.getAllReservations());}

    private void searchByHotel() { hotelGrid.setItems(hotelFacade.findByHotelName(hotelName.getValue()));
    }

    private void searchByCountry() { hotelGrid.setItems(hotelFacade.findByLocationCountry(locationCountry.getValue()));
    }

    private void searchByCity() { hotelGrid.setItems(hotelFacade.findByLocationCity(locationCity.getValue()));
    }

    private void searchByFoodOption() { hotelGrid.setItems(hotelFacade.findByFoodOption(String.valueOf(foodOption.getValue())));
    }

    private void searchByRating() { hotelGrid.setItems(hotelFacade.findByHotelOfficialRating(String.valueOf(hotelOfficialRating.getValue())));
    }

    private void searchByDuration() { //hotelGrid.setItems(hotelFacade.findByDuration(String.valueOf(duration.getValue())));
    }
}






