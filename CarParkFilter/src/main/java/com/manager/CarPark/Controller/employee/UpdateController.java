package com.manager.CarPark.Controller.employee;

import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.EmployeeDto;
import com.manager.CarPark.Model.EmployeeModel;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.EmployeeService;
import com.manager.CarPark.Util.Validator.EmployeeValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller("EmployeeUpdateController")
@RequestMapping("/admin_dashboard/employee/list/view/update")
public class UpdateController {

    @Autowired
    private EmployeeService o_employeeService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private EmployeeValidator o_validator;

    @Autowired
    private ModelMapper o_modelMapper;

    @GetMapping
    public String getUpdate(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            @RequestParam(required = false) String id,
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


        List<EmployeeDto> c_employeeDto = o_employeeService.findById(id);
        if(c_employeeDto.size()!=0){
            EmployeeDto o_employeeDto = c_employeeDto.get(0);
            model.addAttribute("employee",o_employeeDto);
            return "/admin/employee/update";
        }
        return "redirect:/admin_dashboard/employee/list";
    }

    @PostMapping
    public String handleUpdate(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            EmployeeModel o_employeeModel
    ) {
        CredentialDto o_credential = new CredentialDto(ACCOUNT, ROLE, JSESSIONID, ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized = o_authenticationService.isAuthorized(o_credential, "ADMIN");
        if (!b_isAuthenticated)
            return "redirect:/login";
        else if (!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account", ACCOUNT);

        EmployeeDto o_employeeDto = o_modelMapper.map(o_employeeModel,EmployeeDto.class);
        HashMap<String, Boolean> c_validToken = o_validator.validateUpdate(o_employeeDto);
        if (o_validator.isValid(c_validToken)) {
            EmployeeDto o_employee = o_modelMapper.map(o_employeeModel, EmployeeDto.class);
            Optional<EmployeeDto> o_employeeDtoOp = o_employeeService.update(o_employee);
            if (o_employeeDtoOp.isPresent()) {
                model.addAttribute("notification","Update Employee Success");
            }else{
                model.addAttribute("notification","Update Employee Fail");
            }
            return "/admin/employee/notification";
        } else {
            model.addAttribute("employee", o_employeeModel);
            c_validToken.forEach(model::addAttribute);
            return "/admin/employee/update";
        }
    }
}
