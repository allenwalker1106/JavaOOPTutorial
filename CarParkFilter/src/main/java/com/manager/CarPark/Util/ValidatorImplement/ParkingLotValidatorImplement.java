package com.manager.CarPark.Util.ValidatorImplement;

import com.manager.CarPark.DTO.ParkingLotDto;
import com.manager.CarPark.Repository.ParkingLotRepository;
import com.manager.CarPark.Util.Validator.ParkingLotValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ParkingLotValidatorImplement extends ValidatorImplement implements ParkingLotValidator {

    @Autowired
    private ParkingLotRepository o_parkingLotRepository;

    @Override
    public boolean existPlot(String name, String place, Long area) {
        try{
            return o_parkingLotRepository.existPlot(name,place,area);
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public HashMap<String, Boolean> validate(ParkingLotDto o_parkingLotDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        if(existPlot(o_parkingLotDto.getName(),o_parkingLotDto.getPlace(),o_parkingLotDto.getArea())){

            c_token.put("isNameValid",false);
            c_token.put("isPlaceValid",false);
            c_token.put("isAreaValid",false);
        }else{
            c_token.put("isNameValid",this.notEmpty(o_parkingLotDto.getName()));
            c_token.put("isPlaceValid",this.notEmpty(o_parkingLotDto.getPlace()));
            c_token.put("isAreaValid",this.isPositive(o_parkingLotDto.getArea()));
        }
        c_token.put("isPriceValid",this.isPositive(o_parkingLotDto.getPrice()));
        return c_token;
    }

    @Override
    public HashMap<String, Boolean> validateUpdate(ParkingLotDto o_parkingLotDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        if(existPlot(o_parkingLotDto.getName(),o_parkingLotDto.getPlace(),o_parkingLotDto.getArea())){

            c_token.put("isNameValid",false);
            c_token.put("isPlaceValid",false);
            c_token.put("isAreaValid",false);
        }else{
            c_token.put("isNameValid",this.notEmpty(o_parkingLotDto.getName()));
            c_token.put("isPlaceValid",this.notEmpty(o_parkingLotDto.getPlace()));
            c_token.put("isAreaValid",this.isPositive(o_parkingLotDto.getArea()));
        }
        c_token.put("isPriceValid",this.isPositive(o_parkingLotDto.getPrice()));
        return c_token;
    }
}
