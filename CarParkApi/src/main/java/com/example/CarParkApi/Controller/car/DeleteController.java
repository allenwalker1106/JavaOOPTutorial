package com.example.CarParkApi.Controller.car;


import com.example.CarParkApi.DTO.CarDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("CarDeleteController")
@RequestMapping("/employee/car/delete")
public class DeleteController {


    @Autowired
    private CarService o_carService;


    @PostMapping
    public ResponseEntity<ResponseObject> handleDelete(
            @RequestParam(required = true) String id
    ){

        List<CarDto> c_carDto = o_carService.findById(id);
        if(c_carDto.size()!=0){
            CarDto o_carDto = c_carDto.get(0);
            if(o_carService.delete(o_carDto)){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Delete Car",
                                o_carDto
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Delete Car",
                                o_carDto
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

        }else{
            return new ResponseEntity<ResponseObject>(
                    new ResponseObject(
                            HttpStatus.EXPECTATION_FAILED,
                            "Car Doesn't Exist",
                            id
                    ),
                    HttpStatus.EXPECTATION_FAILED
            );
        }
    }
}
