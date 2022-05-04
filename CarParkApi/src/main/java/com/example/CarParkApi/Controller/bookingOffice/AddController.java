package com.example.CarParkApi.Controller.bookingOffice;


import com.example.CarParkApi.DTO.BookingOfficeDto;
import com.example.CarParkApi.Entity.BookingOffice;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.BookingOfficeService;
import com.example.CarParkApi.Service.TripService;
import com.example.CarParkApi.Util.Converter;
import com.example.CarParkApi.Util.Validator.BookingOfficeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController("BookingOfficeAddController")
@RequestMapping("/employee/bookingOffice/add")
public class AddController {
    @Autowired
    private BookingOfficeService o_bookingOfficeService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private BookingOfficeValidator o_validator;

    @Autowired
    private TripService o_tripService;

    @PostMapping
    public ResponseEntity<ResponseObject> handleAdd(
            @RequestBody BookingOfficeDto o_bookingOfficeDto
    ){
        HashMap<String,Boolean> c_criteria = o_validator.validate(o_bookingOfficeDto);
        if(o_validator.isValid(c_criteria)){
            BookingOffice o_bookingOffice = o_converter.DtoToBookingOffice(o_bookingOfficeDto);
            o_bookingOffice.setStartDate(LocalDate.parse(o_bookingOfficeDto.getStartDate()));
            o_bookingOffice.setEndDate(LocalDate.parse(o_bookingOfficeDto.getEndDate()));
            Optional<BookingOfficeDto> o_bookingOfficeDB = o_bookingOfficeService.save(o_bookingOffice);

            if(o_bookingOfficeDB.isPresent()){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Add Booking Office",
                                o_bookingOfficeDB.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Add Booking Office",
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
