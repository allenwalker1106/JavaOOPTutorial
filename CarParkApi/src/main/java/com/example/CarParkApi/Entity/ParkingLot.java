package com.example.CarParkApi.Entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="parking_lots")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="parking_id",nullable=false,unique=true)
    private Long id;

    @Column(name="park_area",nullable=false)
    private Long area;

    @Column(name="park_name",nullable=false)
    private String name;

    @Column(name="park_place",nullable=false)
    private String place;


    @Column(name="park_price",nullable=false)
    private Long price;


    @Column(name="park_status",nullable=false)
    private String status;

    @OneToMany(mappedBy="parkingLot")
    private Set<Car> cars;


    public ParkingLot() {
    }

    public ParkingLot(Long area, String name, String place, Long price, String status) {
        this.area = area;
        this.name = name;
        this.place = place;
        this.price = price;
        this.status = status;
    }

    public ParkingLot(Long id, Long area, String name, String place, Long price, String status) {
        this.id = id;
        this.area = area;
        this.name = name;
        this.place = place;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", area=" + area +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", cars=" + cars +
                '}';
    }
}
