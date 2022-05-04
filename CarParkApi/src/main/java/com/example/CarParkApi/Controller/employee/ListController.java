package com.example.CarParkApi.Controller.employee;

import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.Model.Criteria;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("EmployeeListController")
@RequestMapping("/admin/employee/list")
public class ListController {

    @Autowired
    private EmployeeService o_employeeService;

    @GetMapping
    public ResponseEntity<ResponseObject> getList(
            @RequestBody(required = false) Criteria o_criteria
    ){
        List<EmployeeDto> c_employeeDto;
        try {
            switch (o_criteria.getField().toUpperCase()) {
                case "NAME":
                    c_employeeDto = o_employeeService.findByName(o_criteria.getData());
                    break;
                case "DEPARTMENT":
                    c_employeeDto = o_employeeService.findByDepartment(o_criteria.getData());
                    break;
                case "ID":
                    c_employeeDto = o_employeeService.findById(o_criteria.getData());
                    break;
                default:
                    c_employeeDto = o_employeeService.findAll();
                    break;
            }
        }catch(NullPointerException e){
            c_employeeDto = o_employeeService.findAll();
        }

        sort(c_employeeDto, o_criteria);
        c_employeeDto = paging(c_employeeDto, o_criteria);

        return new ResponseEntity<ResponseObject>(
                new ResponseObject(
                        HttpStatus.OK,
                        "Success",
                        c_employeeDto
                ),
                HttpStatus.OK
        );
    }

    public void sort(List<EmployeeDto> c_employeeDto ,Criteria o_criteria){
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

    public List<EmployeeDto> paging(List<EmployeeDto> c_employeeDto ,Criteria o_criteria){
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
