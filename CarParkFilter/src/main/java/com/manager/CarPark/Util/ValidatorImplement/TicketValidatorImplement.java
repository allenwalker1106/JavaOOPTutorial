package com.manager.CarPark.Util.ValidatorImplement;

import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.TicketDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Repository.CarRepository;
import com.manager.CarPark.Repository.TripRepository;
import com.manager.CarPark.Util.Validator.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TicketValidatorImplement extends ValidatorImplement implements TicketValidator {

    @Autowired
    private TripRepository o_tripRepository;

    @Autowired
    private CarRepository o_carRepository;

    @Override
    public boolean validateTrip(TripDto o_tripDto) {
        try{
            return o_tripRepository.existsById(o_tripDto.getId());
        }catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public boolean validateCar(CarDto o_carDto) {
        try{
            return o_carRepository.existsById(o_carDto.getId());
        }catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public HashMap<String, Boolean> validate(TicketDto o_ticketDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isNameValid",this.validateName(o_ticketDto.getName()));
        c_token.put("isTimeValid",this.validateTime(o_ticketDto.getTime()));
        c_token.put("isTripValid",this.validateTrip(o_ticketDto.getTrip()));
        c_token.put("isCarValid",this.validateCar(o_ticketDto.getCar()));
        return c_token;
    }

}
