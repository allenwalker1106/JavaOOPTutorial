package com.example.CarParkApi.Util.Validator;


import com.example.CarParkApi.DTO.TripDto;

import java.util.HashMap;

public interface TripValidator extends Validator {
    HashMap<String,Boolean> validate(TripDto o_tripDto);
    HashMap<String,Boolean> validateUpdate(TripDto o_tripDto);
}
