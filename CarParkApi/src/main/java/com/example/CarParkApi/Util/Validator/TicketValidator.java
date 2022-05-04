package com.example.CarParkApi.Util.Validator;


import com.example.CarParkApi.DTO.CarDto;
import com.example.CarParkApi.DTO.TicketDto;
import com.example.CarParkApi.DTO.TripDto;

import java.util.HashMap;

public interface TicketValidator extends Validator{
    boolean validateTrip(TripDto o_tripDto);
    boolean validateCar(CarDto o_carDto);
    HashMap<String,Boolean> validate(TicketDto o_ticketDto);
//    HashMap<String,Boolean> validateUpdate(TicketDto o_ticketDto);
}
