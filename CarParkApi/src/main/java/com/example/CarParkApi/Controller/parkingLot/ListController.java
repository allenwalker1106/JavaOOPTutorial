package com.example.CarParkApi.Controller.parkingLot;

import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.DTO.ParkingLotDto;
import com.example.CarParkApi.Model.Criteria;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.ParkingLotService;
import com.example.CarParkApi.Util.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ParkingLotListController")
@RequestMapping("/employee/parkingLot/list")
public class ListController {

    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private Converter o_converter;

    @GetMapping
    public ResponseEntity<ResponseObject> getList(
            @RequestBody(required = false) Criteria o_criteria
    ){


        List<ParkingLotDto> c_parkingLostDto ;
        try {
            switch (o_criteria.getField().toUpperCase()) {
                case "Name":
                    c_parkingLostDto=o_parkingLotService.findByName(o_criteria.getData());
                    break;
                case "PLACE":
                    c_parkingLostDto=o_parkingLotService.findByPlace(o_criteria.getData());
                    break;
                case "AREA":
                    c_parkingLostDto=o_parkingLotService.findByArea(o_criteria.getData());
                    break;
                case "PRICE":
                    c_parkingLostDto=o_parkingLotService.findByPrice(o_criteria.getData());
                    break;
                case "STATUS":
                    c_parkingLostDto=o_parkingLotService.findByStatus(o_criteria.getData());
                    break;
                case "ID":
                    c_parkingLostDto=o_parkingLotService.findById(o_criteria.getData());
                    break;
                default:
                    c_parkingLostDto=o_parkingLotService.findAll();
                    break;
            }
        }catch(NullPointerException e){
            c_parkingLostDto = o_parkingLotService.findAll();
        }

        sort(c_parkingLostDto, o_criteria);
        c_parkingLostDto = paging(c_parkingLostDto, o_criteria);

        return new ResponseEntity<ResponseObject>(
                new ResponseObject(
                        HttpStatus.OK,
                        "Success",
                        c_parkingLostDto
                ),
                HttpStatus.OK
        );
    }

    public void sort(List<ParkingLotDto> c_employeeDto , Criteria o_criteria){
        try{
            boolean asc=true;
            if(o_criteria!=null&&o_criteria.getOrder()!=null){
                switch(o_criteria.getOrder().toUpperCase()){
                    case "ASC":
                        asc=true;
                        break;
                    case "DESC":
                        asc=false;
                        break;
                }
            }
            switch(o_criteria.getOrderBy().toUpperCase()){
                case "ID":
                    if(asc) {
                        c_employeeDto.sort((employeeDto, t1) -> {
                            if (employeeDto.getId() == t1.getId())
                                return 0;
                            else
                                return employeeDto.getId() - t1.getId() > 0 ? 1 : -1;
                        });
                    }
                    else{
                        c_employeeDto.sort((employeeDto, t1) -> {
                            if(employeeDto.getId()==t1.getId())
                                return 0;
                            else
                                return employeeDto.getId()-t1.getId() > 0 ? -1:1;
                        });
                    }
                    break;
                case "PRICE":
                    if(asc) {
                        c_employeeDto.sort((employeeDto, t1) -> {
                            if (employeeDto.getPrice() == t1.getPrice())
                                return 0;
                            else
                                return employeeDto.getPrice() - t1.getPrice() > 0 ? 1 : -1;
                        });
                    }
                    else{
                        c_employeeDto.sort((employeeDto, t1) -> {
                            if(employeeDto.getPrice()==t1.getPrice())
                                return 0;
                            else
                                return employeeDto.getPrice()-t1.getPrice() > 0 ? -1:1;
                        });
                    }
                    break;
                case "NAME":
                    if(asc)
                        c_employeeDto.sort((e, t1) -> {
                            if(e.getName().equals(t1.getName()))
                                return 0;
                            return e.getName().compareTo(t1.getName())>0? 1:-1;
                        });
                    else
                        c_employeeDto.sort((e, t1) -> {
                            if(e.getName().equals(t1.getName()))
                                return 0;
                            return e.getName().compareTo(t1.getName())>0? -1:1;
                        });
                    break;
            }
        }catch(NullPointerException e){
        }
    }

    public List<ParkingLotDto> paging(List<ParkingLotDto> c_employeeDto ,Criteria o_criteria){
        try{
            int page = o_criteria.getPage();
            int limit = o_criteria.getLimit();
            if(limit!=0)
                c_employeeDto = c_employeeDto.subList(page*limit,(page+1*limit));
        }catch(NullPointerException|IndexOutOfBoundsException e){

        }
        return c_employeeDto;
    }
}
