package com.example.CarParkApi.Util.ValidatorImplement;

import com.example.CarParkApi.DTO.EmployeeDto;
import com.example.CarParkApi.Entity.Employee;
import com.example.CarParkApi.Model.EmployeeModel;
import com.example.CarParkApi.Repository.EmployeeRepository;
import com.example.CarParkApi.Util.Validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        try{
            c_token.put("isConfirmPasswordValid",employee.getPassword().equals(employee.getConfirmPassword()));
        }catch(NullPointerException e)
        {
            c_token.put("isConfirmPasswordValid",false);
        }
        c_token.put("isDateValid",this.validateDate(employee.getDate()));
        return c_token;
    }

    @Override
    public List<HashMap<String, Boolean>> validate(List<EmployeeModel> c_employeeModel) {
        List<HashMap<String, Boolean>> c_tokenList = new ArrayList<>();
        c_employeeModel.forEach(e -> c_tokenList.add(validate(e)));
        return c_tokenList;
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
