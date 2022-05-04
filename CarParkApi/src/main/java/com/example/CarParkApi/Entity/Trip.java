package com.example.CarParkApi.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="trips")
public class Trip {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="trip_id",nullable=false,unique=true)
    private Long id;

    @Column(name="booked_ticket_number")
    private Long bookedTicketNumber;

    @Column(name="car_type",nullable=false)
    private String carType;

    @Column(name="departure_date",nullable = false)
    private LocalDate departureDate;

    @Column(name="departure_time",nullable=false)
    private LocalTime departureTime;

    @Column(name="destination",nullable=false)
    private String destination;

    @Column(name="driver",nullable=false)
    private String driver;

    @Column(name="maximum_online_ticket_number",nullable=false)
    private Long maximumOnlineTicketNumber;

    @OneToMany(mappedBy="trip",fetch=FetchType.LAZY)
    private Set<BookingOffice> bookingOffices;


    @OneToMany(mappedBy="trip",fetch=FetchType.LAZY)
    private Set<Ticket> tickets;

    public Trip() {
        bookingOffices = new HashSet<>();
        tickets = new HashSet<>();
    }

    public Trip(Long bookedTicketNumber, String carType, LocalDate departureDate, LocalTime departureTime, String destination, String driver, Long maximumOnlineTicketNumber) {
        this.bookedTicketNumber = bookedTicketNumber;
        this.carType = carType;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.driver = driver;
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }

    public Trip(Long bookedTicketNumber, String carType, LocalDate departureDate, LocalTime departureTime, String destination, String driver, Long maximumOnlineTicketNumber, Set<BookingOffice> bookingOffices, Set<Ticket> tickets) {
        this.bookedTicketNumber = bookedTicketNumber;
        this.carType = carType;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.driver = driver;
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
        this.bookingOffices = bookingOffices;
        this.tickets = tickets;
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
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

    public Set<BookingOffice> getBookingOffices() {
        return bookingOffices;
    }

    public void setBookingOffices(Set<BookingOffice> bookingOffices) {
        this.bookingOffices = bookingOffices;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Long getMaximumOnlineTicketNumber() {
        return maximumOnlineTicketNumber;
    }

    public void setMaximumOnlineTicketNumber(Long maximumOnlineTicketNumber) {
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", bookedTicketNumber=" + bookedTicketNumber +
                ", carType='" + carType + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", destination='" + destination + '\'' +
                ", driver='" + driver + '\'' +
                ", maximumOnlineTicketNumber=" + maximumOnlineTicketNumber +
                ", bookingOffices=" + bookingOffices +
                ", tickets=" + tickets +
                '}';
    }
}
