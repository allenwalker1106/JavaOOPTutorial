package com.example.CarParkApi.Controller.trip;


import com.example.CarParkApi.DTO.TripDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.TripService;
import com.example.CarParkApi.Util.Validator.TripValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller("TripUpdateController")
@RequestMapping("/employee/trip/update")
public class UpdateController {

    @Autowired
    private TripService o_tripService;

    @Autowired
    private TripValidator o_validator;

    @PutMapping
    public ResponseEntity<ResponseObject> handleUpdate(
            @RequestBody TripDto o_tripDto
    ) {
        HashMap<String, Boolean> c_criteria = o_validator.validateUpdate(o_tripDto);
        if (o_validator.isValid(c_criteria)) {
            Optional<TripDto> o_tripDtoOp = o_tripService.update(o_tripDto);
            if (o_tripDtoOp.isPresent()) {
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Update Employee",
                                o_tripDtoOp.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Update Employee",
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
