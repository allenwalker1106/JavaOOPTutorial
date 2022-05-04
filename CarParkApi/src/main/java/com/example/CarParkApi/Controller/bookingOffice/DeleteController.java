package com.example.CarParkApi.Controller.bookingOffice;


import com.example.CarParkApi.DTO.BookingOfficeDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.BookingOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("BookingOfficeDeleteController")
@RequestMapping("/employee/bookingOffice/delete")
public class DeleteController {

    @Autowired
    private BookingOfficeService o_bookingOfficeService;


    @DeleteMapping
    public ResponseEntity<ResponseObject> handleDelete(
            @RequestParam String id
    ){
        List<BookingOfficeDto> c_bookingOfficeDto = o_bookingOfficeService.findById(id);
        if(c_bookingOfficeDto.size()!=0){
            BookingOfficeDto o_bookingOfficeDto = c_bookingOfficeDto.get(0);
            if(o_bookingOfficeService.delete(o_bookingOfficeDto)){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Delete Booking Office",
                                o_bookingOfficeDto
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Delete Booking Office",
                                o_bookingOfficeDto
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

        }else{
            return new ResponseEntity<ResponseObject>(
                    new ResponseObject(
                            HttpStatus.EXPECTATION_FAILED,
                            "Trip Doesn't Exist",
                            id
                    ),
                    HttpStatus.EXPECTATION_FAILED
            );
        }
    }
}
