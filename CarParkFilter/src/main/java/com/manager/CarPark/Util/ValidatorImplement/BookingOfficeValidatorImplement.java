package com.manager.CarPark.Util.ValidatorImplement;

import com.manager.CarPark.DTO.BookingOfficeDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Entity.BookingOffice;
import com.manager.CarPark.Repository.BookingOfficeRepository;
import com.manager.CarPark.Repository.TripRepository;
import com.manager.CarPark.Util.Validator.BookingOfficeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BookingOfficeValidatorImplement extends ValidatorImplement implements BookingOfficeValidator {
    @Autowired
    private TripRepository o_tripRepository;

    @Autowired
    private BookingOfficeRepository o_bookingOfficeRepository;


    @Override
    public boolean validateTrip(TripDto o_tripDto) {
        try{
            return o_tripRepository.existsById(o_tripDto.getId());
        }catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public boolean validatePlace(String place) {
        if(notEmpty(place)){
            return o_bookingOfficeRepository.findByPlace(place).size()==0;
        }
        return false;
    }

    @Override
    public boolean validatePhoneNumber(String phone){
        if(super.validatePhoneNumber(phone)){
            return o_bookingOfficeRepository.findByPhoneNumber(phone).size()==0;
        }
        return false;
    }




    @Override
    public HashMap<String, Boolean> validate(BookingOfficeDto o_bookingOfficeDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isNameValid",this.notEmpty(o_bookingOfficeDto.getName()));
        c_token.put("isTripValid",this.validateTrip(o_bookingOfficeDto.getTrip()));
        c_token.put("isPhoneNumberValid",this.validatePhoneNumber(o_bookingOfficeDto.getPhoneNumber()));
        c_token.put("isPlaceValid",this.validatePlace(o_bookingOfficeDto.getPlace()));
        c_token.put("isPriceValid",this.isPositive(o_bookingOfficeDto.getPrice()));
        c_token.put("isStartDateValid",this.validateDate(o_bookingOfficeDto.getStartDate()));
        c_token.put("isEndDateValid",this.validateDate(o_bookingOfficeDto.getEndDate()));
        return c_token;
    }

    @Override
    public HashMap<String, Boolean> validateUpdate(BookingOfficeDto o_bookingOfficeDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isNameValid",this.notEmpty(o_bookingOfficeDto.getName()));
        c_token.put("isPriceValid",this.isPositive(o_bookingOfficeDto.getPrice()));

        BookingOffice o_bookingOffice = o_bookingOfficeRepository.getById(o_bookingOfficeDto.getId());
        if(!o_bookingOffice.getPhoneNumber().equals(o_bookingOfficeDto.getPhoneNumber()))
            c_token.put("isPhoneNumberValid",this.validatePhoneNumber(o_bookingOfficeDto.getPhoneNumber()));

        if(!o_bookingOffice.getPlace().equals(o_bookingOfficeDto.getPlace()))
            c_token.put("isPlaceValid",this.notEmpty(o_bookingOfficeDto.getPlace()));
        return c_token;
    }
}
