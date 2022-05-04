package com.example.CarParkApi.Controller.employee;

import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("EmployeeDeleteController")
@RequestMapping("/admin/employee/delete")
public class DeleteController {

    @Autowired
    private EmployeeService o_employeeService;

    @DeleteMapping
    public ResponseEntity<ResponseObject> delete(
            @RequestParam(required = true) String id
    ){
        List<EmployeeDto> c_employeeDto = o_employeeService.findById(id);
        if(c_employeeDto.size()!=0){
            EmployeeDto o_employeeDto = c_employeeDto.get(0);
            if(o_employeeService.delete(o_employeeDto)){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Delete Employee",
                                o_employeeDto
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Delete Employee",
                                o_employeeDto
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
