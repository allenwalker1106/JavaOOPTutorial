package com.example.CarParkApi.Controller.bookingOffice;


import com.example.CarParkApi.DTO.BookingOfficeDto;
import com.example.CarParkApi.DTO.CredentialDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.BookingOfficeService;
import com.example.CarParkApi.Util.Validator.BookingOfficeValidator;
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

@RestController("BookingOfficeUpdateController")
@RequestMapping("/employee/bookingOffice/update")
public class UpdateController {

    @Autowired
    private BookingOfficeService o_bookingOfficeService;

    @Autowired
    private BookingOfficeValidator o_validator;

    @Autowired
    private ModelMapper o_modelMapper;

    @PutMapping
    public ResponseEntity<ResponseObject> handleUpdate(
            @RequestBody BookingOfficeDto o_bookingOfficeDto
    ) {

        HashMap<String, Boolean> c_criteria = o_validator.validateUpdate(o_bookingOfficeDto);
        if (o_validator.isValid(c_criteria)) {

            Optional<BookingOfficeDto> o_bookingOfficeDtoOp = o_bookingOfficeService.update(o_bookingOfficeDto);
            if (o_bookingOfficeDtoOp.isPresent()) {
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Update Booking Office",
                                o_bookingOfficeDtoOp.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Update Booking Office",
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
