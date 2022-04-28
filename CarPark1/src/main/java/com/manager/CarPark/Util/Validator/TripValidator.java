package com.manager.CarPark.Util.Validator;

import com.manager.CarPark.DTO.TripDto;

import java.util.HashMap;

public interface TripValidator extends Validator {
    HashMap<String,Boolean> validate(TripDto o_tripDto);
    HashMap<String,Boolean> validateUpdate(TripDto o_tripDto);
}
