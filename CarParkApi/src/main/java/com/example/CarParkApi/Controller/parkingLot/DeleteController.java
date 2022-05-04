package com.example.CarParkApi.Controller.parkingLot;

import com.example.CarParkApi.DTO.ParkingLotDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.CarService;
import com.example.CarParkApi.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ParkingLotDeleteController")
@RequestMapping("/employee/parkingLot/delete")
public class DeleteController {

    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private CarService o_carService;

    @DeleteMapping
    public ResponseEntity<ResponseObject> handleDelete(
            @RequestParam String id
    ){

        List<ParkingLotDto> c_parkingLotDto = o_parkingLotService.findById(id);
        if(c_parkingLotDto.size()!=0){
            ParkingLotDto o_parkingLotDto = c_parkingLotDto.get(0);
            if(o_parkingLotService.delete(o_parkingLotDto)){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Delete Parking Lot",
                                o_parkingLotDto
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Delete Parking Lot",
                                o_parkingLotDto
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
