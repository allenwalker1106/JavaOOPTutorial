package com.example.CarParkApi.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @Column(name = "license_plate", nullable = false, unique = true)
    private String id;

    @Column(name = "car_color", nullable = false)
    private String color;

    @Column(name = "car_type", nullable = false)
    private String type;

    @Column(name = "company", nullable = false)
    private String company;

    @OneToMany(mappedBy = "car")
    private Set<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "park_id")
    private ParkingLot parkingLot;

    public Car() {
    }

    public Car(String color, String type, String company) {
        this.color = color;
        this.type = type;
        this.company = company;
    }

    public Car(String id, String color, String type, String company) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", tickets=" + tickets +
                ", parkingLot=" + parkingLot +
                '}';
    }
}