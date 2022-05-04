package com.example.CarParkApi.DTO;

import java.io.Serializable;
import java.util.Objects;

public class TicketDto implements Serializable {
    private Long id;
    private String time;
    private String name;
    private TripDto trip;
    private CarDto car;

    public TicketDto() {
    }

    public TicketDto(Long id, String time, String name, TripDto trip, CarDto car) {
        this.id = id;
        this.time = time;
        this.name = name;
        this.trip = trip;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TripDto getTrip() {
        return trip;
    }

    public void setTrip(TripDto trip) {
        this.trip = trip;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto entity = (TicketDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.time, entity.time) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.trip, entity.trip) &&
                Objects.equals(this.car, entity.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, name, trip, car);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "time = " + time + ", " +
                "name = " + name + ", " +
                "trip = " + trip + ", " +
                "car = " + car + ")";
    }
}
