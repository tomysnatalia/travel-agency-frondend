package com.travel.agency.frontend;

import com.travel.agency.frontend.domain.Flight;
import com.travel.agency.frontend.service.FlightService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class FlightForm extends FormLayout {

    private Binder <Flight>binder = new Binder<Flight>(Flight.class);

    private TextField departure = new TextField("Departure");
    private TextField arrival = new TextField("Arrival");
    private IntegerField price = new IntegerField("Price");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private MainView mainView;

    private FlightService service = FlightService.getInstance();

    public FlightForm(MainView mainView) {
        this.mainView = mainView;
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(departure, arrival, price);
        add(buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    private void save() {
       Flight flight = binder.getBean();
        service.save(flight);
        mainView.refreshFlight();
        setFlight(null);
    }

    private void delete() {
        Flight flight = binder.getBean();
        service.delete(flight);
        mainView.refreshFlight();
        setFlight(null);
    }

    public void setFlight(Flight flight){
        binder.setBean(flight);

        if(flight == null) {
            setVisible(false);
        } else {
            setVisible(true);
            departure.focus();
            //arrival.focus();
        }
    }
}
