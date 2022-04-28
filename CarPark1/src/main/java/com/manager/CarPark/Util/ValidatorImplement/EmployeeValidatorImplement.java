package com.manager.CarPark.Util.ValidatorImplement;

import com.manager.CarPark.DTO.EmployeeDto;
import com.manager.CarPark.Entity.Employee;
import com.manager.CarPark.Model.EmployeeModel;
import com.manager.CarPark.Repository.EmployeeRepository;
import com.manager.CarPark.Util.Validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EmployeeValidatorImplement extends ValidatorImplement implements EmployeeValidator {
    @Autowired
    private EmployeeRepository o_employeeRepository;


    @Override
    public boolean validateEmail(String email){
        if(super.validateEmail(email)){
            return o_employeeRepository.findByEmail(email).size()==0;
        }
        return false;
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber){
        if(super.validatePhoneNumber(phoneNumber)){
            return o_employeeRepository.findByPhoneNumber(phoneNumber).size()==0;
        }
        return false;
    }


    @Override
    public boolean validateAccount(String account) {
        if(notEmpty(account)){
            return o_employeeRepository.findByAccount(account).size()==0;
        }
        return false;
    }

    @Override
    public HashMap<String, Boolean> validate(EmployeeModel employee) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isNameValid",this.validateName(employee.getName()));
        c_token.put("isDepartmentValid",this.notEmpty(employee.getDepartment()));
        c_token.put("isAddressValid",this.notEmpty(employee.getAddress()));
        c_token.put("isEmailValid",this.validateEmail(employee.getEmail()));
        c_token.put("isPhoneNumberValid",this.validatePhoneNumber(employee.getPhoneNumber()));
        c_token.put("isGenderValid",this.notEmpty(employee.getGender()));
        c_token.put("isAccountValid",this.validateAccount(employee.getAccount()));
        c_token.put("isPasswordValid",this.validatePassword(employee.getPassword()));
        c_token.put("isConfirmPasswordValid",employee.getPassword().equals(employee.getConfirmPassword()));
        c_token.put("isDateValid",this.validateDate(employee.getDate()));
        return c_token;
    }

    @Override
    public HashMap<String, Boolean> validateUpdate(EmployeeDto o_employeeDto) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        boolean isNameValid = false,
                isDepartmentValid = false,
                isAddressValid = false,
                isEmailValid = false,
                isPhoneNumberValid = false;

        if(o_employeeRepository.existsById(o_employeeDto.getId())){
            Employee o_employee         = o_employeeRepository.getById(o_employeeDto.getId());
            isNameValid                 = this.validateName(o_employeeDto.getName());
            isDepartmentValid           = this.notEmpty(o_employeeDto.getDepartment());
            isAddressValid              = this.notEmpty(o_employeeDto.getAddress());
            if(!o_employee.getEmail().equals(o_employeeDto.getEmail()))
                isEmailValid            = this.validateEmail(o_employeeDto.getEmail());
            else
                isEmailValid            = true;

            if(!o_employee.getPhoneNumber().equals(o_employeeDto.getPhoneNumber()))
                isPhoneNumberValid      = this.validatePhoneNumber(o_employeeDto.getPhoneNumber());
            else
                isPhoneNumberValid      = true;

        }

        c_token.put("isNameValid",isNameValid);
        c_token.put("isDepartmentValid",isDepartmentValid);
        c_token.put("isAddressValid",isAddressValid);
        c_token.put("isEmailValid",isEmailValid);
        c_token.put("isPhoneNumberValid",isPhoneNumberValid);

        return c_token;
    }
}
