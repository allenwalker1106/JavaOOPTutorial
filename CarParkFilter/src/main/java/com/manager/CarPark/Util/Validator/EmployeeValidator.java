package com.manager.CarPark.Util.Validator;

import com.manager.CarPark.DTO.EmployeeDto;
import com.manager.CarPark.Model.EmployeeModel;

import java.util.HashMap;

public interface EmployeeValidator extends Validator {
    boolean validateAccount(String account);
    HashMap<String,Boolean> validate(EmployeeModel o_employeeModel);
    HashMap<String,Boolean> validateUpdate(EmployeeDto o_employeeDto);
}
