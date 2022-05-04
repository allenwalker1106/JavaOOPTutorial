package com.example.CarParkApi.Controller.trip;

import com.example.CarParkApi.DTO.TripDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("TripDeleteController")
@RequestMapping("/employee/trip/delete")
public class DeleteController {

    @Autowired
    private TripService o_tripService;


    @DeleteMapping
    public ResponseEntity<ResponseObject> handleDelete(
            @RequestParam String id
    ){
        List<TripDto> c_tripDto = o_tripService.findById(id);
        if(c_tripDto.size()!=0){
            TripDto o_tripDto = c_tripDto.get(0);
            if(o_tripService.delete(o_tripDto)){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Delete Trip",
                                o_tripDto
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Delete Trip",
                                o_tripDto
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
