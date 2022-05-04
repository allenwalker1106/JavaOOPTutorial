package com.manager.CarPark.Controller.employee;

import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.EmployeeDto;
import com.manager.CarPark.Entity.Employee;
import com.manager.CarPark.Model.EmployeeModel;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.EmployeeService;
import com.manager.CarPark.Util.Converter;
import com.manager.CarPark.Util.Validator.EmployeeValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;


@Controller("EmployeeAddController")
@RequestMapping("/admin_dashboard/employee/add")
public class AddController {

    @Autowired
    private EmployeeService o_employeeService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private EmployeeValidator o_validator;

    @Autowired
    private ModelMapper o_modelMapper;



    @GetMapping
    public String getAdd(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model
    ){
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"ADMIN");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);


        model.addAttribute("employee",new EmployeeModel());
        return "/admin/employee/add";
    }



    @PostMapping
    public String handleAdd(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            EmployeeModel o_employeeModel
    ){
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"ADMIN");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);


        model.addAttribute("account",ACCOUNT);

        HashMap<String,Boolean> c_validToken = o_validator.validate(o_employeeModel);
        if(o_validator.isValid(c_validToken)){
            Employee o_employee = o_modelMapper.map(o_employeeModel,Employee.class);
            o_employee.setDate(LocalDate.parse(o_employeeModel.getDate()));
            Optional<EmployeeDto> o_employeeDto = o_employeeService.save(o_employee);
            if(o_employeeDto.isPresent()){
                model.addAttribute("notification","Add Employee Success");
            }else{
                model.addAttribute("notification","Add Employee Fail");
            }
            return "/admin/employee/notification";
        }else{

            model.addAttribute("employee",o_employeeModel);
            c_validToken.forEach(model::addAttribute);
            return "/admin/employee/add";
        }
    }
}
