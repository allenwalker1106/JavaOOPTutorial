package com.example.CarParkApi.Controller.employee;


import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.EmployeeService;
import com.example.CarParkApi.Util.Converter;
import com.example.CarParkApi.Util.Validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController("EmployeeUpdateController")
@RequestMapping("/admin/employee/update")
public class UpdateController {

    @Autowired
    private EmployeeService o_employeeService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private EmployeeValidator o_validator;

    @PutMapping
    public ResponseEntity<ResponseObject> update(
            @RequestBody EmployeeDto o_employeeDto
    ) {
        HashMap<String, Boolean> c_criteria = o_validator.validateUpdate(o_employeeDto);
        if (o_validator.isValid(c_criteria)) {
            Optional<EmployeeDto> o_employeeDtoOp = o_employeeService.update(o_employeeDto);
            if (o_employeeDtoOp.isPresent()) {
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Update Employee",
                                o_employeeDtoOp.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Update Employee",
                                ""
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        } else {
            return new ResponseEntity<ResponseObject>(
                    new ResponseObject(
                            HttpStatus.EXPECTATION_FAILED,
                            "Invalid Input Data Or Duplicate Entry",
                            c_criteria
                    ),
                    HttpStatus.EXPECTATION_FAILED
            );
        }
    }
}
