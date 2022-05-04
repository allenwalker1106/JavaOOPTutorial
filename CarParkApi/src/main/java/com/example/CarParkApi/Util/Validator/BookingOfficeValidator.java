package com.example.CarParkApi.Util.Validator;

import com.example.CarParkApi.DTO.BookingOfficeDto;
import com.example.CarParkApi.DTO.TripDto;
import java.util.HashMap;

public interface BookingOfficeValidator extends Validator {
    boolean validateTrip(TripDto o_tripDto);
    boolean validatePlace(String place);
    HashMap<String,Boolean> validate(BookingOfficeDto o_bookingOfficeDto);
    HashMap<String,Boolean> validateUpdate(BookingOfficeDto o_bookingOfficeDto);
}
