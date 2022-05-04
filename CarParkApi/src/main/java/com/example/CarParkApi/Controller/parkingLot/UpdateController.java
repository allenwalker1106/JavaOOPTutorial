package com.example.CarParkApi.Controller.parkingLot;


import com.example.CarParkApi.DTO.ParkingLotDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.ParkingLotService;
import com.example.CarParkApi.Util.Validator.ParkingLotValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController("ParkingLotUpdateController")
@RequestMapping("/employee/parkingLot/update")
public class UpdateController {
    @Autowired
    private ParkingLotService o_parkingLotService;


    @Autowired
    private ParkingLotValidator o_validator;

    @PutMapping
    public ResponseEntity<ResponseObject> handleUpdate(
            @RequestBody ParkingLotDto o_parkingLotDto
    ) {

        HashMap<String, Boolean> c_criteria = o_validator.validateUpdate(o_parkingLotDto);
        if (o_validator.isValid(c_criteria)) {

            Optional<ParkingLotDto> o_parkingLotDtoOp = o_parkingLotService.update(o_parkingLotDto);
            if (o_parkingLotDtoOp.isPresent()) {
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Update Parking Lot",
                                o_parkingLotDtoOp.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Update Parking Lot",
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
