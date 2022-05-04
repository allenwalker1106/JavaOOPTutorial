package com.example.CarParkApi.DTO;

import java.io.Serializable;
import java.util.Objects;

public class BookingOfficeDto implements Serializable {
    private Long id;
    private String endDate;
    private String name;
    private String phoneNumber;
    private String place;
    private Long price;
    private String startDate;
    private TripDto trip;

    public BookingOfficeDto() {
    }

    public BookingOfficeDto(Long id, String endDate, String name, String phoneNumber, String place, Long price, String startDate, TripDto trip) {
        this.id = id;
        this.endDate = endDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.place = place;
        this.price = price;
        this.startDate = startDate;
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public TripDto getTrip() {
        return trip;
    }

    public void setTrip(TripDto trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingOfficeDto entity = (BookingOfficeDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.endDate, entity.endDate) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                Objects.equals(this.place, entity.place) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.trip, entity.trip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endDate, name, phoneNumber, place, price, startDate, trip);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "endDate = " + endDate + ", " +
                "name = " + name + ", " +
                "phoneNumber = " + phoneNumber + ", " +
                "place = " + place + ", " +
                "price = " + price + ", " +
                "startDate = " + startDate + ", " +
                "trip = " + trip + ")";
    }
}
