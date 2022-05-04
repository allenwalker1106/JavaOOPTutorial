package com.example.CarParkApi.Controller.parkingLot;



import com.example.CarParkApi.DTO.ParkingLotDto;
import com.example.CarParkApi.Entity.ParkingLot;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.ParkingLotService;
import com.example.CarParkApi.Util.Converter;
import com.example.CarParkApi.Util.Validator.ParkingLotValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;


@RestController("ParkingLotAddController")
@RequestMapping("/employee/parkingLot/add")
public class AddController {
    @Autowired
    private ParkingLotService o_parkingLotService;


    @Autowired
    private Converter o_converter;

    @Autowired
    private ParkingLotValidator o_validator;

    @Autowired
    private ModelMapper o_modelMapper;

    @PostMapping
    public ResponseEntity<ResponseObject> handleAdd(
            @RequestBody ParkingLotDto o_parkingLotDto
    ){


        HashMap<String,Boolean> c_criteria = o_validator.validate(o_parkingLotDto);

        if(o_validator.isValid(c_criteria)){
            ParkingLot o_parkingLot = o_converter.DtoToParkingLot(o_parkingLotDto);
            Optional<ParkingLotDto> o_parkingLotDB = o_parkingLotService.save(o_parkingLot);

            if(o_parkingLotDB.isPresent()){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Add Parking Lot",
                                o_parkingLotDB.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Add Parking Lot",
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
