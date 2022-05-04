package com.example.CarParkApi.Controller.employee;

import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.Model.EmployeeModel;
import com.example.CarParkApi.Model.ResponseObject;
import com.example.CarParkApi.Service.EmployeeService;
import com.example.CarParkApi.Util.Converter;
import com.example.CarParkApi.Util.Validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController("EmployeeAddController")
@RequestMapping("/admin/employee")
public class AddController {

    @Autowired
    private EmployeeService o_employeeService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private EmployeeValidator o_validator;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> save(@RequestBody EmployeeModel o_employeeModel){
        HashMap<String,Boolean> c_criteria = o_validator.validate(o_employeeModel);
        if(o_validator.isValid(c_criteria)){
            Optional<EmployeeDto> o_employeeDtoOp=o_employeeService.save(o_converter.ModelToEntity(o_employeeModel));
            if(o_employeeDtoOp.isPresent()){
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Add Employee",
                                o_employeeDtoOp.get()
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseObject>(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Add Employee",
                                ""
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }else{
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

    @PostMapping("/addAll")
    public ResponseEntity<ResponseObject> saveAll(@RequestBody List<EmployeeModel> c_employeeModel){
        List<HashMap<String,Boolean>> c_criteria = o_validator.validate(c_employeeModel);
        if(o_validator.isValid(c_criteria)) {
            List<Optional<EmployeeDto>> c_employeeDtoOp = o_employeeService.save(o_converter.ModelToEntity(c_employeeModel));
            List<ResponseObject> c_response = new ArrayList<>();
            for (Optional<EmployeeDto> o_employeeDtoOp : c_employeeDtoOp) {
                if (o_employeeDtoOp.isPresent()) {
                    c_response.add(new ResponseObject(
                            HttpStatus.OK,
                            "Success Add Employee",
                            o_employeeDtoOp.get()
                    ));
                } else {
                    c_response.add(new ResponseObject(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Fail Add Employee",
                            ""
                    ));
                }
            }
            return new ResponseEntity<ResponseObject>(
                    new ResponseObject(
                            HttpStatus.OK,
                            "Success Process",
                            c_response
                    ),
                    HttpStatus.OK
            );
        }else{
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
