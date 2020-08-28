package com.travel.agency.frontend.form;

import com.travel.agency.frontend.MainView;
import com.travel.agency.frontend.backend.reservation.ReservationFacade;
import com.travel.agency.frontend.domain.Reservation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
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
    private ReservationFacade reservationFacade;

    private TextField id = new TextField("id");
    private TextField flightId= new TextField("flightId");
    private TextField hotelId = new TextField ("hotelId");
    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField price = new TextField("price");

    private TextField phoneNumber = new TextField("phone number");
    private TextField numberOfAdults = new TextField("adults");
    private TextField numberOfKids= new TextField("kids");

    private Button save = new Button("save", VaadinIcon.CHECK.create());
    private Button delete= new Button("delete", VaadinIcon.TRASH.create());
    private Button back = new Button("back", VaadinIcon.ARROW_BACKWARD.create());

    private Binder<Reservation> binder = new Binder<>(Reservation.class);

    @Autowired
    public ReservationForm(ReservationFacade reservationFacade, MainView mainView) {
        this.reservationFacade = reservationFacade;
        this.mainView = mainView;
        HorizontalLayout buttons = new HorizontalLayout(save, delete, back);
        add(id, flightId, hotelId, name, surname, email, phoneNumber, numberOfAdults, numberOfKids, price, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        back.addClickListener(event -> back());
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

    public void save() {
        Reservation reservation = binder.getBean();
        if (reservation.getId().isEmpty()) {
            reservationFacade.createReservation(reservation);
        } else {
            reservationFacade.updateReservation(reservation);
        }
        mainView.refreshReservation();
        setReservation(null);
    }

    public void delete() {
        Reservation reservation = binder.getBean();
        reservationFacade.deleteReservation(Long.valueOf(reservation.getId()));

        mainView.refreshReservation();
        setReservation(null);
    }

    public void back() {
        setReservation(null);
    }
}