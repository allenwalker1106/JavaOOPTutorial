package com.example.CarParkApi.Controller.car;

import com.example.CarParkApi.DTO.CarDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.CarService;
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

@RestController("CarUpdateController")
@RequestMapping("/employee/car/update")
public class UpdateController {
    @Autowired
    private CarService o_carService;
    @Autowired
    private CarValidator o_validator;

    @PutMapping
    public ResponseEntity<ResponseObject> handleUpdate(
            @RequestBody CarDto o_carDto
    ) {

        HashMap<String, Boolean> c_criteria = o_validator.validateUpdate(o_carDto);
        if (o_validator.isValid(c_criteria)) {
            Optional<CarDto> o_carDtoOP = o_carService.update(o_carDto);
            if (o_carDtoOP.isPresent()) {
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Update Car",
                                o_carDtoOP.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Update Car",
                                ""
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        } else {
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
