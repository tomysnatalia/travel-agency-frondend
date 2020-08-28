package com.travel.agency.frontend.form;

import com.travel.agency.frontend.MainView;
import com.travel.agency.frontend.backend.hotel.HotelFacade;
import com.travel.agency.frontend.domain.hotel.Hotel;
import com.vaadin.flow.component.button.Button;
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
public class HotelForm extends FormLayout {

    private MainView mainView;
    private HotelFacade hotelFacade;

    private TextField id = new TextField("id");
    private TextField hotelName = new TextField("hotel");
    private TextField locationCity = new TextField("city");
    private TextField locationCountry = new TextField("country");
    private TextField hotelOfficialRating = new TextField("stars rating");
    private TextField foodOption = new TextField("food option");
    private TextField duration = new TextField("duration");
    private TextField closerAirport = new TextField("closer airport");
    private TextField pricePerNightForAdult = new TextField("price - adult");
    private TextField pricePerNightForKid = new TextField("price - kid");

    private Button save = new Button("save", VaadinIcon.CHECK.create());
    private Button delete= new Button("delete", VaadinIcon.TRASH.create());
    private Button back = new Button("back", VaadinIcon.ARROW_BACKWARD.create());

    Binder<Hotel> binder = new Binder<>(Hotel.class);

    @Autowired
    public HotelForm(HotelFacade hotelFacade, MainView mainView) {
        this.hotelFacade= hotelFacade;
        this.mainView = mainView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete, back);
        add(id, hotelName, locationCountry, locationCity, closerAirport, hotelOfficialRating, foodOption, duration, pricePerNightForAdult, pricePerNightForKid, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        back.addClickListener(event -> back());
    }

    public void setHotel(Hotel hotel) {
        binder.setBean(hotel);
        if (hotel == null) {
            setVisible(false);
        } else {
            setVisible(true);
            hotelName.focus();
        }
    }

    public void save() {
        Hotel hotel = binder.getBean();
        if(hotel.getId().isEmpty()) {
            hotelFacade.createHotel(hotel);
        } else {
            hotelFacade.updateHotel(hotel);
        }
        mainView.refreshHotel();
        setHotel(null);
    }

    public void delete() {
        Hotel hotel = binder.getBean();
        hotelFacade.deleteHotel(Long.parseLong(hotel.getId()));

        mainView.refreshHotel();
        setHotel(null);
    }

    public void back() {
        setHotel(null);
    }
}



