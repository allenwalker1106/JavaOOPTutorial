package com.example.CarParkApi.Controller.trip;

import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.DTO.TripDto;
import com.example.CarParkApi.Model.Criteria;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.TripService;
import com.example.CarParkApi.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("TripListController")
@RequestMapping("/employee/trip/list")
public class ListController {

    @Autowired
    private TripService o_tripService;


    @Autowired
    private Converter o_converter;


    @GetMapping
    public ResponseEntity<ResponseObject> getList(
            @RequestBody(required = false) Criteria o_criteria
    ){
        List<TripDto> c_tripDto ;
        try{
            switch (o_criteria.getField().toUpperCase()){
                case "DRIVER":
                    c_tripDto=o_tripService.findByDriver(o_criteria.getData());
                    break;
                case "DESTINATION":
                    c_tripDto=o_tripService.findByDestination(o_criteria.getData());
                    break;
                case "DATE":
                    c_tripDto=o_tripService.findByDate(o_criteria.getData());
                    break;
                case "ID":
                    c_tripDto=o_tripService.findById(o_criteria.getData());
                    break;
                case "TIME":
                    c_tripDto=o_tripService.findByTime(o_criteria.getData());
                    break;
                default:
                    c_tripDto=o_tripService.findAll();
                    break;
            }
        }catch(NullPointerException e){
            c_tripDto=o_tripService.findAll();
        }


        sort(c_tripDto, o_criteria);
        c_tripDto = paging(c_tripDto, o_criteria);

        return new ResponseEntity<ResponseObject>(
                new ResponseObject(
                        HttpStatus.OK,
                        "Success",
                        c_tripDto
                ),
                HttpStatus.OK
        );
    }

    public void sort(List<TripDto> c_tripDto , Criteria o_criteria){
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
                        c_tripDto.sort((employeeDto, t1) -> {
                            if (employeeDto.getId() == t1.getId())
                                return 0;
                            else
                                return employeeDto.getId() - t1.getId() > 0 ? 1 : -1;
                        });
                    }
                    else{
                        c_tripDto.sort((employeeDto, t1) -> {
                            if(employeeDto.getId()==t1.getId())
                                return 0;
                            else
                                return employeeDto.getId()-t1.getId() > 0 ? -1:1;
                        });
                    }
                    break;
                case "DESTINATION":
                    if(asc)
                        c_tripDto.sort((e, t1) -> {
                            if(e.getDestination().equals(t1.getDestination()))
                                return 0;
                            return e.getDestination().compareTo(t1.getDestination())>0? 1:-1;
                        });
                    else
                        c_tripDto.sort((e, t1) -> {
                            if(e.getDestination().equals(t1.getDestination()))
                                return 0;
                            return e.getDestination().compareTo(t1.getDestination())>0? -1:1;
                        });
                    break;
            }
        }catch(NullPointerException e){
        }
    }

    public List<TripDto> paging(List<TripDto> c_tripDto ,Criteria o_criteria){
        try{
            int page = o_criteria.getPage();
            int limit = o_criteria.getLimit();
            if(limit!=0)
                c_tripDto = c_tripDto.subList(page*limit,(page+1*limit));
        }catch(NullPointerException|IndexOutOfBoundsException e){

        }
        return c_tripDto;
    }
}
