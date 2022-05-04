package com.example.CarParkApi.Controller.ticket;


import com.example.CarParkApi.DTO.TicketDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("TicketDeleteController")
@RequestMapping("/employee/ticket/delete")
public class DeleteController {
    @Autowired
    private TicketService o_ticketService;

    @DeleteMapping
    public ResponseEntity<ResponseObject> handleDelete(
            @RequestParam(required = true) String id
    ){

        List<TicketDto> c_ticketDto = o_ticketService.findById(id);
        if(c_ticketDto.size()!=0){
            TicketDto o_ticketDto = c_ticketDto.get(0);
            if(o_ticketService.delete(o_ticketDto)){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Delete Ticket",
                                o_ticketDto
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Delete Employee",
                                o_ticketDto
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

        }else{
            return new ResponseEntity<ResponseObject>(
                    new ResponseObject(
                            HttpStatus.EXPECTATION_FAILED,
                            "Employee Doesn't Exist",
                            id
                    ),
                    HttpStatus.EXPECTATION_FAILED
            );
        }
    }
}
