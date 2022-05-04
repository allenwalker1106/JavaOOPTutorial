package com.example.CarParkApi.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="booking_offices")
public class BookingOffice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="office_id",nullable=false,unique=true)
    private Long id;

    @Column(name="end_contract_date")
    private LocalDate endDate;

    @Column(name="office_name",nullable=false)
    private String name;

    @Column(name="office_phone",nullable=false,unique=true)
    private String phoneNumber;

    @Column(name="office_place",nullable=false,unique=true)
    private String place;

    @Column(name="office_price")
    private Long price;

    @Column(name="start_contract_date")
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name="trip_id")
    private Trip trip;

    public BookingOffice() {
    }

    public BookingOffice(LocalDate endDate, String name, String phoneNumber, String place, Long price, LocalDate startDate) {
        this.endDate = endDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.place = place;
        this.price = price;
        this.startDate = startDate;
    }

    public BookingOffice(Long id, LocalDate endDate, String name, String phoneNumber, String place, Long price, LocalDate startDate) {
        this.id = id;
        this.endDate = endDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.place = place;
        this.price = price;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "BookingOffice{" +
                "id=" + id +
                ", endDate=" + endDate +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", place='" + place + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", trip=" + trip +
                '}';
    }
}
