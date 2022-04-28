package com.manager.CarPark.Entity;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name="tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ticket_id",nullable=false,unique=true)
    private Long id;

    @Column(name="booking_time",nullable=false)
    private LocalTime time;

    @Column(name="customer_name",nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name="trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name="license_plate")
    private Car car;

    public Ticket() {
    }

    public Ticket(LocalTime time, String name) {
        this.time = time;
        this.name = name;
    }

    public Ticket(Long id, LocalTime time, String name) {
        this.id = id;
        this.time = time;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
