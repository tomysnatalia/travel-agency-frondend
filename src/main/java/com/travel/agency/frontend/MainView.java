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
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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

    private Button buttonFlight = new Button("Flights", VaadinIcon.AIRPLANE.create());
    private Button buttonHotels = new Button("Hotels", VaadinIcon.HOME.create());
    private Button buttonReservation = new Button("Reservation", VaadinIcon.WALLET.create());

    private TextField flightId = new TextField("id");
    private TextField departure = new TextField("departure");
    private TextField arrival = new TextField("arrival");
    private DatePicker departureDate = new DatePicker("departure date");
    private DatePicker returnDate = new DatePicker("return date");
    private TextField flightNumber = new TextField("flight number");

    private TextField hotelId = new TextField("id");
    private TextField hotelName = new TextField("hotel");
    private TextField locationCountry = new TextField("country");
    private TextField locationCity = new TextField("city");
    private ComboBox hotelOfficialRating = new ComboBox<>("hotel stars");
    private ComboBox foodOption = new ComboBox<>("food option");
    private ComboBox duration = new ComboBox<>("duration");

    private TextField reservationId = new TextField("reservation number");
    private TextField surname = new TextField("surname");
    private TextField paymentDate = new TextField("payment date");

    private Button addFlight = new Button("new flight", VaadinIcon.PLUS.create());
    private Button addHotel = new Button("new hotel", VaadinIcon.PLUS.create());
    private Button addReservation = new Button("new reservation", VaadinIcon.PLUS.create());

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

            HorizontalLayout mainContent = new HorizontalLayout(flightId ,departure, arrival, flightNumber, departureDate, returnDate);
            add(mainContent);
            flightGrid.setColumns("id", "departure", "arrival", "departureDate", "returnDate", "flightNumber", "price");
            add(flightGrid, flightForm);

            flightId.setClearButtonVisible(true);
            flightId.addValueChangeListener(f -> searchByFlightId());

            departure.setClearButtonVisible(true);
            departure.addValueChangeListener(f -> searchByFlightDeparture());

            arrival.setClearButtonVisible(true);
            arrival.addValueChangeListener(f -> searchByFlightArrival());

            flightNumber.setClearButtonVisible(true);
            flightNumber.addValueChangeListener(f -> searchByFlightNumber());

            departureDate.addValueChangeListener(f -> searchFlightByDepartureDate());
            returnDate.addValueChangeListener(f -> searchFlightByReturnDate());

            flightGrid.setSizeFull();
            refreshFlight();
            add(flightForm);
            flightForm.setFlight(null);
            flightGrid.asSingleSelect().addValueChangeListener(event -> flightForm.setFlight(flightGrid.asSingleSelect().getValue()));
        });

        buttonHotels.addClickListener(e -> {
            removeAll();
            HorizontalLayout toolBar3 = new HorizontalLayout(hotelId, buttonFlight, buttonHotels, buttonReservation, addHotel);
            add(toolBar3);
            addHotel.addClickListener(add -> {
                hotelGrid.asSingleSelect().clear();
                hotelForm.setHotel(new Hotel());
            });

            hotelForm.setHotel(null);

            hotelGrid.setColumns("id", "hotelName", "locationCountry", "locationCity", "closerAirport", "hotelOfficialRating", "foodOption", "duration", "pricePerNightForAdult", "pricePerNightForKid");
            HorizontalLayout horizontalLayout = new HorizontalLayout(hotelId, hotelName, locationCountry, locationCity);
            HorizontalLayout horizontalLayout2 = new HorizontalLayout(hotelOfficialRating, foodOption, duration);
            add(horizontalLayout, horizontalLayout2);
            add(hotelGrid);

            hotelId.setClearButtonVisible(true);
            hotelId.addValueChangeListener(h -> searchByHotelId());

            hotelName.setClearButtonVisible(true);
            hotelName.addValueChangeListener(h -> searchByHotelName());

            locationCountry.setClearButtonVisible(true);
            locationCountry.addValueChangeListener(h -> searchByCountry());

            locationCity.setClearButtonVisible(true);
            locationCity.addValueChangeListener(h -> searchByCity());

            hotelOfficialRating.setItems(HotelRating.values());
            hotelOfficialRating.setPlaceholder("Rating stars");
            hotelOfficialRating.addValueChangeListener(h -> searchByHotelRating());

            foodOption.setItems(FoodOption.values());
            foodOption.setPlaceholder("Food options");
            foodOption.addValueChangeListener(h -> searchByFoodOption());

            duration.setItems(DurationOption.values());
            duration.setPlaceholder("Duration");
            duration.addValueChangeListener(h -> searchByDuration());

            hotelGrid.setSizeFull();
            refreshHotel();
            add(hotelForm);
            hotelForm.setHotel(null);
            hotelGrid.asSingleSelect().addValueChangeListener(event -> hotelForm.setHotel(hotelGrid.asSingleSelect().getValue()));
        });

        buttonReservation.addClickListener(e -> {
            removeAll();
            HorizontalLayout toolBar4 = new HorizontalLayout(buttonFlight, buttonHotels, buttonReservation, addReservation);
            add(toolBar4);
            addReservation.addClickListener(add -> {
                reservationGrid.asSingleSelect().clear();
                reservationForm.setReservation(new Reservation());
            });

            reservationForm.setReservation(null);

            reservationGrid.setColumns("id", "flightId", "hotelId", "name", "surname", "email", "phoneNumber", "numberOfAdults", "numberOfKids", "hotelPrice", "deposit", "paymentStatus", "paymentDepositStatus", "paymentDate", "hotelPriceWithFlight");
            HorizontalLayout hotelLayout = new HorizontalLayout(reservationId, surname, paymentDate);
            add(hotelLayout);
            add(reservationGrid);

            reservationId.setClearButtonVisible(true);
            reservationId.addValueChangeListener(event -> searchByReservationNumber());

            surname.setClearButtonVisible(true);
            surname.addValueChangeListener(event -> searchReservationBySurname());

            setSizeFull();
            refreshReservation();
            add(reservationForm);
            reservationForm.setReservation(null);
            reservationGrid.asSingleSelect().addValueChangeListener(event -> reservationForm.setReservation(reservationGrid.asSingleSelect().getValue()));
        });
    }

    public void refreshFlight() { flightGrid.setItems(flightFacade.getAllFlights());}

    public void refreshHotel() { hotelGrid.setItems(hotelFacade.getAllHotels());}

    public void refreshReservation() { reservationGrid.setItems(reservationFacade.getAllReservations());}


    public void searchByFlightId() { flightGrid.setItems(flightFacade.findFlightById(Long.valueOf(flightId.getValue())));}

    public void searchByFlightDeparture() { flightGrid.setItems(flightFacade.findByDeparture(departure.getValue()));}

    public void searchByFlightArrival() { flightGrid.setItems(flightFacade.findByArrival(arrival.getValue()));}

    public void searchByFlightNumber() { flightGrid.setItems(flightFacade.findByFlightNumber(flightNumber.getValue()));}

    public void searchFlightByDepartureDate() { flightGrid.setItems(flightFacade.findByDepartureDate(String.valueOf(departureDate.getValue()))); }

    public void searchFlightByReturnDate(){ flightGrid.setItems(flightFacade.findByReturnDate(String.valueOf(returnDate.getValue()))); }


    public void searchByHotelId() {hotelGrid.setItems(hotelFacade.findHotelById(Long.valueOf(hotelId.getValue())));}

    public void searchByHotelName() { hotelGrid.setItems(hotelFacade.findByHotelName(hotelName.getValue())); }

    public void searchByCountry() { hotelGrid.setItems(hotelFacade.findByLocationCountry(locationCountry.getValue())); }

    public void searchByCity() { hotelGrid.setItems(hotelFacade.findByLocationCity(locationCity.getValue())); }

    public void searchByFoodOption() { hotelGrid.setItems(hotelFacade.findByFoodOption(String.valueOf(foodOption.getValue()))); }

    public void searchByHotelRating() { hotelGrid.setItems(hotelFacade.findByHotelOfficialRating(String.valueOf((hotelOfficialRating.getValue())))); }

    public void searchByDuration() { hotelGrid.setItems(hotelFacade.findByDuration(String.valueOf(duration.getValue()))); }


    public void searchByReservationNumber() { reservationGrid.setItems(reservationFacade.findReservationById(Long.valueOf(reservationId.getValue()))); }

    public void searchReservationBySurname() { reservationGrid.setItems(reservationFacade.findReservationBySurname(surname.getValue()));}
}






