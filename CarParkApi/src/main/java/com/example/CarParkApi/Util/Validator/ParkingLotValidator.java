package com.example.CarParkApi.Util.Validator;


import com.example.CarParkApi.DTO.ParkingLotDto;

import java.util.HashMap;

public interface ParkingLotValidator extends Validator {
    boolean existPlot(String name,String place,Long area);
    HashMap<String,Boolean> validate(ParkingLotDto o_parkingLotDto);
    HashMap<String,Boolean> validateUpdate(ParkingLotDto o_parkingLotDto);
}
