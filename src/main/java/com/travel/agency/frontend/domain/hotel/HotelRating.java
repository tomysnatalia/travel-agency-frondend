package com.travel.agency.frontend.domain.hotel;

public enum HotelRating {
    two("2 stars"), three("3 stars"), four("4 stars"), five("5 stars");

    private String value;

    HotelRating (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
