package com.travel.agency.frontend.form;

import com.travel.agency.frontend.MainView;
import com.travel.agency.frontend.backend.reservation.ReservationFacade;
import com.travel.agency.frontend.domain.Reservation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class ReservationForm extends FormLayout {

    private MainView mainView;
    private final ReservationFacade reservationFacade;

    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField price = new TextField("price");

    private TextField reservationId = new TextField("reservationId");
    private TextField flightId= new TextField("flightId");
    private TextField hotelId = new TextField ("hotelId");
    private TextField phoneNumber = new TextField("phoneNumber");
    private TextField numberOfAdults = new TextField("numberOfAdults");
    private TextField numberOfKids= new TextField("numberOfKids");

    private Binder<Reservation> binder = new Binder<>(Reservation.class);

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button delete = new Button("Delete", VaadinIcon.CHECK.create());

    @Autowired
    public ReservationForm(ReservationFacade reservationFacade, MainView mainView) {
        this.reservationFacade = reservationFacade;
        this.mainView = mainView;
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(reservationId, flightId, hotelId, name, surname, email, phoneNumber, numberOfAdults, numberOfKids, buttons
        );
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }


    public void setReservation(Reservation reservation) {
        binder.setBean(reservation);

        if (reservation == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

    void save() {
        Reservation reservation = binder.getBean();

        if(reservation.isSafeToSave() && reservation.getId().equals("")) {
           // reservationFacade.createReservation(reservation);
        } else if(reservation.isSafeToUpdate()) {
           // reservationFacade.updateReservation(reservation);
        } else {
            Notification.show("Fields are not filled properly!");
        }
        mainView.refreshHotel();
        setReservation(null);
    }

    void delete() {
        Reservation reservation = binder.getBean();

        if(reservation.getId().chars().allMatch(Character::isDigit)) {
          //  reservationFacade.deleteReservation(Long.parseLong(reservation.getId() ) );
        }
        mainView.refreshHotel();
        setReservation(null);
    }
}