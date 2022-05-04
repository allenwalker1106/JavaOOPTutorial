package com.example.CarParkApi.Controller.bookingOffice;

import com.example.CarParkApi.DTO.BookingOfficeDto;
import com.example.CarParkApi.DTO.TripDto;
import com.example.CarParkApi.Model.Criteria;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.BookingOfficeService;
import com.example.CarParkApi.Util.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("BookingOfficeListController")
@RequestMapping("/employee/bookingOffice/list")
public class ListController {

    @Autowired
    private BookingOfficeService o_bookingOfficeService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private ModelMapper o_modelMapper;

    @GetMapping
    public ResponseEntity<ResponseObject> getList(
            @RequestBody(required = false) Criteria o_criteria
    ){
        List<BookingOfficeDto> c_bookingOfficeDto ;
        try{
            switch (o_criteria.getField().toUpperCase()){
                case "NAME":
                    c_bookingOfficeDto = o_bookingOfficeService.findByName(o_criteria.getData());
                    break;
                case "PHONE":
                    c_bookingOfficeDto = o_bookingOfficeService.findByPhone(o_criteria.getData());
                    break;
                case "TRIP":
                    c_bookingOfficeDto = o_bookingOfficeService.findByTrip(o_criteria.getData());
                    break;
                case "ID":
                    c_bookingOfficeDto =  o_bookingOfficeService.findById(o_criteria.getData());
                    break;
                default:
                    c_bookingOfficeDto = o_bookingOfficeService.findAll();
                    break;
            }
        }catch(NullPointerException e){
            c_bookingOfficeDto=o_bookingOfficeService.findAll();
        }

        sort(c_bookingOfficeDto, o_criteria);
        c_bookingOfficeDto = paging(c_bookingOfficeDto, o_criteria);

        return new ResponseEntity<ResponseObject>(
                new ResponseObject(
                        HttpStatus.OK,
                        "Success",
                        c_bookingOfficeDto
                ),
                HttpStatus.OK
        );
    }

    public void sort(List<BookingOfficeDto> c_tripDto , Criteria o_criteria){
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
                case "NAME":
                    if(asc)
                        c_tripDto.sort((e, t1) -> {
                            if(e.getName().equals(t1.getName()))
                                return 0;
                            return e.getName().compareTo(t1.getName())>0? 1:-1;
                        });
                    else
                        c_tripDto.sort((e, t1) -> {
                            if(e.getName().equals(t1.getName()))
                                return 0;
                            return e.getName().compareTo(t1.getName())>0? -1:1;
                        });
                    break;
                case "TRIP":
                    if(asc)
                        c_tripDto.sort((e, t1) -> {
                            if(e.getTrip().getDestination().equals(t1.getTrip().getDestination()))
                                return 0;
                            return e.getTrip().getDestination().compareTo(t1.getTrip().getDestination())>0? 1:-1;
                        });
                    else
                        c_tripDto.sort((e, t1) -> {
                            if(e.getTrip().getDestination().equals(t1.getTrip().getDestination()))
                                return 0;
                            return e.getTrip().getDestination().compareTo(t1.getTrip().getDestination())>0? -1:1;
                        });
                    break;
            }
        }catch(NullPointerException e){
        }
    }

    public List<BookingOfficeDto> paging(List<BookingOfficeDto> c_tripDto ,Criteria o_criteria){
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
