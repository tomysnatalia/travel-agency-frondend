package com.travel.agency.frontend.form;

import com.travel.agency.frontend.MainView;
import com.travel.agency.frontend.backend.flight.FlightFacade;
import com.travel.agency.frontend.domain.Flight;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class FlightForm extends FormLayout {

    private MainView mainView;
    private FlightFacade flightFacade;

    private TextField id = new TextField("id");
    private TextField flightNumber = new TextField("flight number");
    private TextField departure = new TextField("departure");
    private TextField arrival = new TextField("arrival");
    private DatePicker departureDate = new DatePicker("departure date");
    private DatePicker returnDate = new DatePicker("return date");
    private TextField price = new TextField("price");

    private Button save = new Button("Save");
    private Button delete= new Button("Delete");
    private Button back = new Button("back");

    Binder<Flight> binder = new Binder<>(Flight.class);

    @Autowired
    public FlightForm(FlightFacade flightFacade, MainView mainView) {
        this.flightFacade= flightFacade;
        this.mainView = mainView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete, back);
        add(id, departure, arrival, departureDate, returnDate, flightNumber, price,  buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        back.addClickListener(event -> back());
    }

    public void setFlight(Flight flight) {
        binder.setBean(flight);
        if (flight == null) {
            setVisible(false);
        } else {
            setVisible(true);
            departure.focus();
        }
    }

    public void save() {
        Flight flight = binder.getBean();
        if(flight.getId().isEmpty()) {
            flightFacade.createFlight(flight);
        } else {
            flightFacade.updateFlight(flight);
        }
        mainView.refreshFlight();
        setFlight(null);

    }

    public void delete() {
        Flight flight = binder.getBean();
        flightFacade.deleteFlight(Long.parseLong(flight.getId()));

        mainView.refreshFlight();
        setFlight(null);
    }

    public void back() {
        setFlight(null);
    }
}