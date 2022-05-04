package com.manager.CarPark.Util.Validator;


import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.TicketDto;
import com.manager.CarPark.DTO.TripDto;

import java.util.HashMap;

public interface TicketValidator extends Validator{
    boolean validateTrip(TripDto o_tripDto);
    boolean validateCar(CarDto o_carDto);
    HashMap<String,Boolean> validate(TicketDto o_ticketDto);
//    HashMap<String,Boolean> validateUpdate(TicketDto o_ticketDto);
}
