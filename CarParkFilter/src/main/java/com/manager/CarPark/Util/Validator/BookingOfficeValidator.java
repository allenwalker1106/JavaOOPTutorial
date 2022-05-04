package com.manager.CarPark.Util.Validator;

import com.manager.CarPark.DTO.BookingOfficeDto;
import com.manager.CarPark.DTO.TripDto;

import java.util.HashMap;

public interface BookingOfficeValidator extends Validator {
    boolean validateTrip(TripDto o_tripDto);
    boolean validatePlace(String place);
    HashMap<String,Boolean> validate(BookingOfficeDto o_bookingOfficeDto);
    HashMap<String,Boolean> validateUpdate(BookingOfficeDto o_bookingOfficeDto);
}
