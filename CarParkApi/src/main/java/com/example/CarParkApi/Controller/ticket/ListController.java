package com.example.CarParkApi.Controller.ticket;


import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.DTO.TicketDto;
import com.example.CarParkApi.Model.Criteria;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.TicketService;
import com.example.CarParkApi.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("TicketListController")
@RequestMapping("/employee/ticket/list")
public class ListController {
    @Autowired
    private TicketService o_tickerService;

    @Autowired
    private Converter o_converter;

    @GetMapping
    public ResponseEntity<ResponseObject> getList(
            @RequestBody(required = false) Criteria o_criteria
    ){
        List<TicketDto> c_ticketDto ;
        try {
            switch (o_criteria.getField().toUpperCase()) {
                case "ID":
                    c_ticketDto=o_tickerService.findById(o_criteria.getData());
                    break;
                default:
                    c_ticketDto=o_tickerService.findAll();
                    break;
            }
        }catch(NullPointerException e){
            c_ticketDto = o_tickerService.findAll();
        }

        sort(c_ticketDto, o_criteria);
        c_ticketDto = paging(c_ticketDto, o_criteria);

        return new ResponseEntity<ResponseObject>(
                new ResponseObject(
                        HttpStatus.OK,
                        "Success",
                        c_ticketDto
                ),
                HttpStatus.OK
        );
    }


    public void sort(List<TicketDto> c_employeeDto , Criteria o_criteria){
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

            }
        }catch(NullPointerException e){
        }
    }

    public List<TicketDto> paging(List<TicketDto> c_employeeDto ,Criteria o_criteria){
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
