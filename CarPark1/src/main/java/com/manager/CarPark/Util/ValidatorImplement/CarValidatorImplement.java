package com.manager.CarPark.Util.ValidatorImplement;

import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.ParkingLotDto;
import com.manager.CarPark.Repository.CarRepository;
import com.manager.CarPark.Service.ParkingLotService;
import com.manager.CarPark.Util.Validator.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CarValidatorImplement extends ValidatorImplement implements CarValidator {

    @Autowired
    private CarRepository o_carRepository;

    @Autowired
    private ParkingLotService o_parkingLotService;

    @Override
    public boolean validateLicense(String license) {
        try{
            return !o_carRepository.existsById(license);
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean validateParkingLot(ParkingLotDto o_parkingLotDto) {
        try{
            return o_parkingLotService.isEmptySpot(o_parkingLotDto);
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public HashMap<String, Boolean> validate(CarDto o_carDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isIdValid",this.validateLicense(o_carDto.getId()));
        c_token.put("isTypeValid",this.notEmpty(o_carDto.getType()));
        c_token.put("isColorValid",this.notEmpty(o_carDto.getColor()));
        c_token.put("isCompanyValid",this.notEmpty(o_carDto.getCompany()));
        c_token.put("isParkingLotValid",this.validateParkingLot(o_carDto.getParkingLot()));
        return c_token;
    }

    @Override
    public HashMap<String, Boolean> validateUpdate(CarDto o_carDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isTypeValid",this.notEmpty(o_carDto.getType()));
        c_token.put("isColorValid",this.notEmpty(o_carDto.getColor()));
        c_token.put("isCompanyValid",this.notEmpty(o_carDto.getCompany()));
        return c_token;
    }
}
