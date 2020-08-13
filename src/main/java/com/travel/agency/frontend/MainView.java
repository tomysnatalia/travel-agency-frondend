package com.travel.agency.frontend;

import com.travel.agency.frontend.domain.Flight;
import com.travel.agency.frontend.domain.Reservation;
import com.travel.agency.frontend.domain.hotel.DurationOption;
import com.travel.agency.frontend.domain.hotel.FoodOption;
import com.travel.agency.frontend.domain.hotel.Hotel;
import com.travel.agency.frontend.domain.hotel.HotelRating;
import com.travel.agency.frontend.form.FlightForm;
import com.travel.agency.frontend.form.HotelForm;
import com.travel.agency.frontend.form.ReservationForm;
import com.travel.agency.frontend.service.FlightService;
import com.travel.agency.frontend.service.HotelService;
import com.travel.agency.frontend.service.ReservationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private ReservationService reservationService = ReservationService.getInstance();
    private FlightService flightService = FlightService.getInstance();
    private HotelService hotelService = HotelService.getInstance();

    private Grid reservationGrid = new Grid<>(Reservation.class);
    private Grid gridFlight = new Grid<>(Flight.class);
    private Grid hotelGrid = new Grid<>(Hotel.class);


    public TextField reservationNumber = new TextField();

    private Button buttonFlight = new Button("Flights");
    private Button buttonHotels = new Button("Hotels");
    private Button buttonReservation = new Button("Reservation");

    private FlightForm flightForm = new FlightForm(this);
    private HotelForm hotelForm = new HotelForm(this);
    private ReservationForm reservationForm = new ReservationForm( this);

    private TextField departure = new TextField("departure");
    private TextField arrival = new TextField("arrival");
    private DatePicker departureDate = new DatePicker("departure date");
    private DatePicker returnDate = new DatePicker("return date");

    private TextField hotelId = new TextField("hotelId");
    private TextField hotelName = new TextField();
    private TextField locationCountry = new TextField();
    private TextField locationCity = new TextField();
    private TextField closerAirport = new TextField();
    private ComboBox hotelOfficialRating = new ComboBox<>("Hotel Stars");
    private ComboBox foodOption = new ComboBox<>("Food option");
    private ComboBox duration = new ComboBox<>("Duration");


    public MainView() {

        HorizontalLayout toolBar = new HorizontalLayout(buttonFlight, buttonHotels, buttonReservation);
        add(toolBar);
        setSizeFull();

        buttonFlight.addClickListener(e -> {
            removeAll();
            add(toolBar);
            HorizontalLayout mainContent = new HorizontalLayout(departure, arrival, departureDate, returnDate);
            add(mainContent);
            gridFlight.setColumns("flightId", "departure", "arrival", "departureDate", "returnDate", "price");
            add(gridFlight);
            departure.setPlaceholder("Filter by departure");
            departure.setClearButtonVisible(true);
            departure.setValueChangeMode(ValueChangeMode.EAGER);
            departure.addValueChangeListener(f -> updateDeparture());

            arrival.setPlaceholder("Filter by arrival");
            arrival.setClearButtonVisible(true);
            arrival.setValueChangeMode(ValueChangeMode.EAGER);
            arrival.addValueChangeListener(f -> updateArrival());
            setSizeFull();
            refreshFlight();
            add(flightForm);
            flightForm.setFlight(null);
            gridFlight.asSingleSelect().addValueChangeListener(event -> flightForm.setFlight((Flight) gridFlight.asSingleSelect().getValue()));

        });

        buttonHotels.addClickListener(e -> {
            removeAll();
            add(toolBar);
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

            closerAirport.setPlaceholder("Airport");
            closerAirport.setClearButtonVisible(true);
            closerAirport.setValueChangeMode(ValueChangeMode.EAGER);
            closerAirport.addValueChangeListener(h -> searchByAirport());

            hotelOfficialRating.setItems(HotelRating.values());
            hotelOfficialRating.setPlaceholder("Rating stars");
            hotelOfficialRating.setClearButtonVisible(true);
            hotelOfficialRating.addValueChangeListener(h -> searchByRating());

            foodOption.setItems(FoodOption.values());
            foodOption.setPlaceholder("Food options");
            foodOption.setClearButtonVisible(true);
            foodOption.addValueChangeListener(h -> searchByFoodOption());

            duration.setItems(DurationOption.values());
            duration.setPlaceholder("Duration");
            duration.setClearButtonVisible(true);
            duration.addValueChangeListener(h -> searchByDuration());

            hotelGrid.setColumns("hotelId", "hotelName", "locationCountry", "locationCity", "closerAirport", "hotelOfficialRating", "foodOption", "duration", "pricePerNightForAdult", "pricePerNightForKid");
            HorizontalLayout toolBar2 = new HorizontalLayout(hotelName, locationCountry, locationCity, closerAirport);
            HorizontalLayout toolBar3 = new HorizontalLayout( hotelOfficialRating, foodOption, duration);
            add(toolBar2, toolBar3);
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
            add(toolBar);
            add(reservationNumber);
            reservationGrid.setColumns("reservationId", "flightId", "hotelId", "name", "surname", "email", "phoneNumber", "numberOfAdults", "numberOfKids", "hotelPrice", "deposit", "paymentStatus", "paymentDepositStatus", "paymentDate", "hotelPriceWithFlight");
            add(reservationGrid);
            reservationNumber.setPlaceholder("Reservation number");
            reservationNumber.setClearButtonVisible(true);
            reservationNumber.addValueChangeListener(event -> search());
            setSizeFull();
            add(reservationForm);
            reservationForm.setReservation(null);
            reservationGrid.asSingleSelect().addValueChangeListener(event -> reservationForm.setReservation((Reservation) reservationGrid.asSingleSelect().getValue())) ;
        });
    }
    public void search () {
        reservationGrid.setItems(reservationService.findById(String.valueOf(reservationNumber.getValue())));
    }

    public void updateDeparture() {
        gridFlight.setItems(flightService.findByDeparture(departure.getValue()));
    }

    public void updateArrival() {
        gridFlight.setItems(flightService.findByArrival(arrival.getValue()));
    }


    public void refreshFlight() {
        gridFlight.setItems(flightService.getFlight());
    }

    public void refreshHotel() {
        hotelGrid.setItems(hotelService.getHotel());
    }

    private void searchByHotel() {
        hotelGrid.setItems(hotelService.findByHotelName(hotelName.getValue()));
    }

    private void searchByCountry() { hotelGrid.setItems(hotelService.findByLocationCountry(locationCountry.getValue())); }

    private void searchByCity() {
        hotelGrid.setItems(hotelService.findByLocationCity(locationCity.getValue()));
    }

    private void searchByAirport() {
        hotelGrid.setItems(hotelService.findByCloserAirport(closerAirport.getValue()));
    }

    private void searchByFoodOption() { hotelGrid.setItems(hotelService.findByFoodOption(String.valueOf(foodOption.getValue()))); }

    private void searchByRating() { hotelGrid.setItems(hotelService.findByHotelOfficialRating(String.valueOf(hotelOfficialRating.getValue()))); }

    private void searchByDuration() { hotelGrid.setItems(hotelService.findByDuration(String.valueOf(duration.getValue()))); }
}






