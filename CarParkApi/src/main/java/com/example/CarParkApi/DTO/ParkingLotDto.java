package com.example.CarParkApi.DTO;

import java.io.Serializable;
import java.util.Objects;

public class ParkingLotDto implements Serializable {
    private Long id;
    private Long area;
    private String name;
    private String place;
    private Long price;
    private String status;

    public ParkingLotDto() {
    }

    public ParkingLotDto(Long id, Long area, String name, String place, Long price, String status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLotDto entity = (ParkingLotDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.area, entity.area) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.place, entity.place) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, area, name, place, price, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "area = " + area + ", " +
                "name = " + name + ", " +
                "place = " + place + ", " +
                "price = " + price + ", " +
                "status = " + status + ")";
    }
}
