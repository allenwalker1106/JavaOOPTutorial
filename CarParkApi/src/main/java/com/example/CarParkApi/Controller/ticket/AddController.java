package com.example.CarParkApi.Controller.ticket;


import com.example.CarParkApi.DTO.TicketDto;
import com.example.CarParkApi.Entity.Ticket;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.CarService;
import com.example.CarParkApi.Service.TicketService;
import com.example.CarParkApi.Service.TripService;
import com.example.CarParkApi.Util.Converter;
import com.example.CarParkApi.Util.Validator.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController("TicketAddController")
@RequestMapping("/employee/ticket/add")
public class AddController {
    @Autowired
    private TicketService o_ticketService;

    @Autowired
    private TripService o_tripService;

    @Autowired
    private CarService o_carService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private TicketValidator o_validator;

    @PostMapping
    public ResponseEntity<ResponseObject> handleAdd(
            @RequestBody TicketDto o_ticketDto
    ){


        HashMap<String,Boolean> c_criteria = o_validator.validate(o_ticketDto);

        if(o_validator.isValid(c_criteria)){
            Ticket o_ticket = o_converter.DtoToTicket(o_ticketDto);
            o_ticket.setTime(LocalTime.parse(o_ticketDto.getTime()));
            Optional<TicketDto> o_ticketDB = o_ticketService.save(o_ticket);

            if(o_ticketDB.isPresent()){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Add Ticket",
                                o_ticketDB.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Add Ticket",
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
