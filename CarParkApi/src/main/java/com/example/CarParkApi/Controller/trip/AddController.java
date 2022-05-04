package com.example.CarParkApi.Controller.trip;

import com.example.CarParkApi.DTO.TripDto;
import com.example.CarParkApi.Entity.Trip;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.TripService;
import com.example.CarParkApi.Util.Converter;
import com.example.CarParkApi.Util.Validator.TripValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Optional;

@RestController("TripAddController")
@RequestMapping("/employee/trip/add")
public class AddController {
    @Autowired
    private TripService o_tripService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private TripValidator o_validator;

    @PostMapping
    public ResponseEntity<ResponseObject> handleAdd(@RequestBody TripDto o_tripDto){
        HashMap<String,Boolean> c_criteria = o_validator.validate(o_tripDto);

        if(o_validator.isValid(c_criteria)){
            Trip o_trip = o_converter.DtoToTrip(o_tripDto);
            o_trip.setDepartureDate(LocalDate.parse(o_tripDto.getDepartureDate().toString()));
            o_trip.setDepartureTime(LocalTime.parse(o_tripDto.getDepartureTime().toString()));
            Optional<TripDto> o_tripDB = o_tripService.save(o_trip);

            if(o_tripDB.isPresent()){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Add Ticket",
                                o_tripDB.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Add Employee",
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
