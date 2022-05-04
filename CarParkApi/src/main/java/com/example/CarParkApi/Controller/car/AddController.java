package com.example.CarParkApi.Controller.car;

import com.example.CarParkApi.DTO.CarDto;
import com.example.CarParkApi.Entity.Car;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.CarService;
import com.example.CarParkApi.Service.ParkingLotService;
import com.example.CarParkApi.Util.Converter;
import com.example.CarParkApi.Util.Validator.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController("CarAddController")
@RequestMapping("/employee/car/add")
public class AddController {
    @Autowired
    private CarService o_carService;

    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private CarValidator o_validator;


    @PostMapping
    public ResponseEntity<ResponseObject> handleAdd(
            @RequestBody CarDto o_carDto
    ){


        HashMap<String,Boolean> c_criteria = o_validator.validate(o_carDto);

        if(o_validator.isValid(c_criteria)){
            Car o_car = o_converter.DtoToCar(o_carDto);
            Optional<CarDto> o_carDB = o_carService.save(o_car);

            if(o_carDB.isPresent()){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Add Car",
                                o_carDB.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Add Car",
                                ""
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

        }else{
            return new ResponseEntity<ResponseObject>(
                    new ResponseObject(
                            HttpStatus.EXPECTATION_FAILED,
                            "Invalid Input Data Or Duplicate Entry",
                            c_criteria
                    ),
                    HttpStatus.EXPECTATION_FAILED
            );
        }
    }

}
