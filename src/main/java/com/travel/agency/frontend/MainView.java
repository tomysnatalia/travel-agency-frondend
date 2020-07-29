package com.travel.agency.frontend;

import com.travel.agency.frontend.domain.Flight;
import com.travel.agency.frontend.service.FlightService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route
public class MainView extends VerticalLayout {

    private FlightService flightService = FlightService.getInstance();
    private Grid grid = new Grid<>(Flight.class);

    public MainView() {
        grid.setColumns("id", "departure", "arrival", "price");
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(flightService.getFlight());
    }

}
