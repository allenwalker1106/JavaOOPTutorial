package com.manager.CarPark.Util.Validator;


import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.ParkingLotDto;

import java.util.HashMap;

public interface CarValidator extends Validator{
    boolean validateLicense(String license);
    boolean validateParkingLot(ParkingLotDto o_parkingLotDto);
    HashMap<String,Boolean> validate(CarDto o_carDto);
    HashMap<String,Boolean> validateUpdate(CarDto o_carDto);
}
