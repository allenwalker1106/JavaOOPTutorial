package com.example.CarParkApi.Util.Validator;


import com.example.CarParkApi.DTO.CarDto;
import com.example.CarParkApi.DTO.ParkingLotDto;

import java.util.HashMap;

public interface CarValidator extends Validator{
    boolean validateLicense(String license);
    boolean validateParkingLot(ParkingLotDto o_parkingLotDto);
    HashMap<String,Boolean> validate(CarDto o_carDto);
    HashMap<String,Boolean> validateUpdate(CarDto o_carDto);
}
