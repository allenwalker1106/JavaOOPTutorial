package com.example.CarParkApi.Util.ValidatorImplement;


import com.example.CarParkApi.DTO.TripDto;
import com.example.CarParkApi.Repository.TripRepository;
import com.example.CarParkApi.Util.Validator.TripValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TripValidatorImplement extends ValidatorImplement implements TripValidator {


    @Autowired
    private TripRepository o_tripRepository;

    @Override
    public HashMap<String, Boolean> validate(TripDto o_tripDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isMaximumOnlineTicketNumberValid",this.isPositive(o_tripDto.getMaximumOnlineTicketNumber()));
        c_token.put("isCarTypeValid",this.notEmpty(o_tripDto.getCarType()));
        c_token.put("isdDestinationValid",this.notEmpty(o_tripDto.getDestination()));
        c_token.put("isDriverValid",this.validateName(o_tripDto.getDriver()));
        c_token.put("isDepartureDateValid",this.validateDate(o_tripDto.getDepartureDate()));
        c_token.put("isDepartureTimeValid",this.validateTime(o_tripDto.getDepartureTime()));
        return c_token;
    }

    @Override
    public HashMap<String, Boolean> validateUpdate(TripDto o_tripDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isIdValid",this.checkIdValid(o_tripDto.getId()));
        c_token.put("isMaximumOnlineTicketNumberValid",this.isPositive(o_tripDto.getMaximumOnlineTicketNumber()));
        c_token.put("isCarTypeValid",this.notEmpty(o_tripDto.getCarType()));
        c_token.put("isdDestinationValid",this.notEmpty(o_tripDto.getDestination()));
        c_token.put("isDriverValid",this.validateName(o_tripDto.getDriver()));
        return c_token;
    }

    public boolean checkIdValid(Long id){
        if(id==null)
            return false;
        try{
            return o_tripRepository.existsById(id);
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}
