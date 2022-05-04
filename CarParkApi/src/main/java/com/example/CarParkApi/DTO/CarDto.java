package com.example.CarParkApi.DTO;

import java.io.Serializable;
import java.util.Objects;

public class CarDto implements Serializable {
    private String id;
    private String color;
    private String type;
    private String company;
    private ParkingLotDto parkingLot;

    public CarDto() {
    }

    public CarDto(String id, String color, String type, String company, ParkingLotDto parkingLot) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.company = company;
        this.parkingLot = parkingLot;
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

    public ParkingLotDto getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLotDto parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto entity = (CarDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.color, entity.color) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.company, entity.company) &&
                Objects.equals(this.parkingLot, entity.parkingLot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, type, company, parkingLot);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "color = " + color + ", " +
                "type = " + type + ", " +
                "company = " + company + ", " +
                "parkingLot = " + parkingLot + ")";
    }
}
