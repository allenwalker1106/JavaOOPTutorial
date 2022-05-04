package com.example.CarParkApi.Util.Validator;


import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.Model.EmployeeModel;

import java.util.HashMap;
import java.util.List;

public interface EmployeeValidator extends Validator {
    boolean validateAccount(String account);
    HashMap<String,Boolean> validate(EmployeeModel o_employeeModel);
    List<HashMap<String,Boolean>> validate(List<EmployeeModel> c_employeeModel);
    HashMap<String,Boolean> validateUpdate(EmployeeDto o_employeeDto);
}
