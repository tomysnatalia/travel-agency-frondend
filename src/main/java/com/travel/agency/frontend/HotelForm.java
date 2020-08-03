package com.travel.agency.frontend;

import com.travel.agency.frontend.domain.hotel.DurationOption;
import com.travel.agency.frontend.domain.hotel.FoodOption;
import com.travel.agency.frontend.domain.hotel.Hotel;
import com.travel.agency.frontend.domain.hotel.HotelRating;
import com.travel.agency.frontend.service.HotelService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


public class HotelForm extends VerticalLayout {

    private HotelService hotelService = HotelService.getInstance();
    private Grid hotelGrid = new Grid<>(Hotel.class);

    private TextField hotelName = new TextField();
    private TextField locationCountry = new TextField();
    private TextField locationCity = new TextField();
    private TextField closerAirport = new TextField();
    private ComboBox hotelOfficialRating = new ComboBox<>("Hotel Stars");
    private ComboBox foodOption = new ComboBox<>("Food option");
    private ComboBox duration = new ComboBox<>("Duration");
    private MainView mainView;


    public HotelForm(MainView mainView) {
        this.mainView = mainView;
        hotelName.setPlaceholder("Hotel name");
        hotelName.setClearButtonVisible(true);
        hotelName.setValueChangeMode(ValueChangeMode.EAGER);
        hotelName.addValueChangeListener(e -> searchByHotel());

        locationCountry.setPlaceholder("Country");
        locationCountry.setClearButtonVisible(true);
        locationCountry.setValueChangeMode(ValueChangeMode.EAGER);
        locationCountry.addValueChangeListener(e -> searchByCountry());

        locationCity.setPlaceholder("City");
        locationCity.setClearButtonVisible(true);
        locationCity.setValueChangeMode(ValueChangeMode.EAGER);
        locationCity.addValueChangeListener(e -> searchByCity());

        closerAirport.setPlaceholder("Airport");
        closerAirport.setClearButtonVisible(true);
        closerAirport.setValueChangeMode(ValueChangeMode.EAGER);
        closerAirport.addValueChangeListener(e -> searchByAirport());

        hotelOfficialRating.setItems(HotelRating.values());
        hotelOfficialRating.setPlaceholder("Rating stars");
        hotelOfficialRating.setClearButtonVisible(true);
        hotelOfficialRating.addValueChangeListener(e -> searchByRating());

        foodOption.setItems(FoodOption.values());
        foodOption.setPlaceholder("Food options");
        foodOption.setClearButtonVisible(true);
        foodOption.addValueChangeListener(e -> searchByFoodOption());

        duration.setItems(DurationOption.values());
        duration.setPlaceholder("Duration");
        duration.setClearButtonVisible(true);
        duration.addValueChangeListener(e -> searchByDuration());

        hotelGrid.setColumns("hotelName", "locationCountry", "locationCity", "closerAirport", "hotelOfficialRating", "foodOption", "duration", "pricePerNightForAdult", "pricePerNightForKid");
        HorizontalLayout toolBar = new HorizontalLayout(hotelName, locationCountry, locationCity, closerAirport);
        HorizontalLayout toolBar2 = new HorizontalLayout( hotelOfficialRating, foodOption, duration);
        add(toolBar, toolBar2);
        add(hotelGrid);
        setSizeFull();
        refreshHotel();

    }

    public void refreshHotel() {
        hotelGrid.setItems(hotelService.getHotel());
    }

    private void searchByHotel() {
        hotelGrid.setItems(hotelService.findByHotelName(hotelName.getValue()));
    }

    private void searchByCountry() {
        hotelGrid.setItems(hotelService.findByLocationCountry(locationCountry.getValue()));
    }

    private void searchByCity() {
        hotelGrid.setItems(hotelService.findByLocationCity(locationCity.getValue()));
    }

    private void searchByAirport() {
        hotelGrid.setItems(hotelService.findByCloserAirport(closerAirport.getValue()));
    }

    private void searchByFoodOption() {
        hotelGrid.setItems(hotelService.findByFoodOption(String.valueOf(foodOption.getValue())));
    }

    private void searchByRating() {
        hotelGrid.setItems(hotelService.findByHotelOfficialRating(String.valueOf(hotelOfficialRating.getValue())));
    }

    private void searchByDuration() {
        hotelGrid.setItems(hotelService.findByDuration(String.valueOf(duration.getValue())));
    }
}



