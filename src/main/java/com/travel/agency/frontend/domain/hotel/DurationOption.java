package com.travel.agency.frontend.domain.hotel;

public enum  DurationOption {
  week(7), twoWeeks(14);

  private int days;

    DurationOption(int days) {
        this.days = days;

    }
}
