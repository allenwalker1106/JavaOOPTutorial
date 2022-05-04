package com.example.CarParkApi.Controller.car;


import com.example.CarParkApi.DTO.CarDto;
import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.Model.Criteria;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.CarService;
import com.example.CarParkApi.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("CarListController")
@RequestMapping("/employee/car/list")
public class ListController {
    @Autowired
    private CarService o_carService;

    @Autowired
    private Converter o_converter;


    @GetMapping
    public ResponseEntity<ResponseObject> getList(
            @RequestBody(required = false) Criteria o_criteria
    ){
        List<CarDto> c_carDto ;
        try {
            switch (o_criteria.getField().toUpperCase()) {
                case "LICENSE":
                    c_carDto=o_carService.findById(o_criteria.getData());
                    break;
                case "COMPANY":
                    c_carDto=o_carService.findByCompany(o_criteria.getData());
                    break;
                default:
                    c_carDto=o_carService.findAll();
                    break;
            }
        }catch(NullPointerException e){
            c_carDto = o_carService.findAll();
        }
        sort(c_carDto, o_criteria);
        c_carDto = paging(c_carDto, o_criteria);

        return new ResponseEntity<ResponseObject>(
                new ResponseObject(
                        HttpStatus.OK,
                        "Success",
                        c_carDto
                ),
                HttpStatus.OK
        );
    }


    public void sort(List<CarDto> c_employeeDto , Criteria o_criteria){
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
                    if(asc)
                        c_employeeDto.sort((e, t1) -> {
                            if(e.getId().equals(t1.getId()))
                                return 0;
                            return e.getId().compareTo(t1.getId())>0? 1:-1;
                        });
                    else
                        c_employeeDto.sort((e, t1) -> {
                            if(e.getId().equals(t1.getId()))
                                return 0;
                            return e.getId().compareTo(t1.getId())>0? -1:1;
                        });
                    break;

                case "COMPANY":
                    if(asc)
                        c_employeeDto.sort((e, t1) -> {
                            if(e.getCompany().equals(t1.getCompany()))
                                return 0;
                            return e.getCompany().compareTo(t1.getCompany())>0? 1:-1;
                        });
                    else
                        c_employeeDto.sort((e, t1) -> {
                            if(e.getCompany().equals(t1.getCompany()))
                                return 0;
                            return e.getCompany().compareTo(t1.getCompany())>0? -1:1;
                        });
                    break;
            }
        }catch(NullPointerException e){
        }
    }

    public List<CarDto> paging(List<CarDto> c_employeeDto ,Criteria o_criteria){
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
