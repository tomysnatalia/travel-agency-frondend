package com.travel.agency.frontend.domain.hotel;

public enum  DurationOption {
  week("7"), twoWeeks("14");

  private String days;

  DurationOption(String days) {
      this.days = days;
  }

  @Override
  public String toString() {
      return days;
  }
}
