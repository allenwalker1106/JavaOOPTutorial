package com.example.CarParkApi.DTO;

import java.io.Serializable;
import java.util.Objects;

public class TripDto implements Serializable {
    private Long id;
    private Long bookedTicketNumber;
    private String carType;
    private String departureDate;
    private String departureTime;
    private String destination;
    private String driver;
    private Long maximumOnlineTicketNumber;

    public TripDto() {
    }

    public TripDto(Long id, Long bookedTicketNumber, String carType, String departureDate, String departureTime, String destination, String driver, Long maximumOnlineTicketNumber) {
        this.id = id;
        this.bookedTicketNumber = bookedTicketNumber;
        this.carType = carType;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.driver = driver;
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookedTicketNumber() {
        return bookedTicketNumber;
    }

    public void setBookedTicketNumber(Long bookedTicketNumber) {
        this.bookedTicketNumber = bookedTicketNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Long getMaximumOnlineTicketNumber() {
        return maximumOnlineTicketNumber;
    }

    public void setMaximumOnlineTicketNumber(Long maximumOnlineTicketNumber) {
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDto entity = (TripDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.bookedTicketNumber, entity.bookedTicketNumber) &&
                Objects.equals(this.carType, entity.carType) &&
                Objects.equals(this.departureDate, entity.departureDate) &&
                Objects.equals(this.departureTime, entity.departureTime) &&
                Objects.equals(this.destination, entity.destination) &&
                Objects.equals(this.driver, entity.driver) &&
                Objects.equals(this.maximumOnlineTicketNumber, entity.maximumOnlineTicketNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookedTicketNumber, carType, departureDate, departureTime, destination, driver, maximumOnlineTicketNumber);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "bookedTicketNumber = " + bookedTicketNumber + ", " +
                "carType = " + carType + ", " +
                "departureDate = " + departureDate + ", " +
                "departureTime = " + departureTime + ", " +
                "destination = " + destination + ", " +
                "driver = " + driver + ", " +
                "maximumOnlineTicketNumber = " + maximumOnlineTicketNumber + ")";
    }
}
