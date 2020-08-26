package com.travel.agency.frontend.form;

import com.travel.agency.frontend.MainView;
import com.travel.agency.frontend.backend.hotel.HotelFacade;
import com.travel.agency.frontend.domain.hotel.Hotel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
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
    private TextField hotelName = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField phoneNumber = new TextField("phoneNumber");
    private TextField numberOfAdults = new TextField("numberOfAdults");
    private TextField numberOfKids= new TextField("numberOfKids");

    private Button save = new Button("Save");
    private Button delete= new Button("Delete");
    private Button back = new Button("back");

    Binder<Hotel> binder = new Binder<>(Hotel.class);

    @Autowired
    public HotelForm(HotelFacade hotelFacade, MainView mainView) {
        this.hotelFacade= hotelFacade;
        this.mainView = mainView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete, back);
        add(id, , surname, email, phoneNumber, numberOfAdults, numberOfKids, buttons);
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



