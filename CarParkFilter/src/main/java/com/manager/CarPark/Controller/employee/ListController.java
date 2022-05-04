package com.manager.CarPark.Controller.employee;

import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.EmployeeDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller("EmployeeListController")
@RequestMapping("/admin_dashboard/employee/list")
public class ListController {

    @Autowired
    private EmployeeService o_employeeService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @GetMapping
    public String getList(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String data,
            @RequestParam(required = false) Long page,
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


        System.out.println(page);
        List<EmployeeDto> c_employeeDto ;
        if(field!=null&&data!=null){
            switch(field.toUpperCase()){
                case "NAME":
                    c_employeeDto=o_employeeService.findByName(data);
                    break;
                case "DEPARTMENT":
                    c_employeeDto=o_employeeService.findByDepartment(data);
                    break;
                case "ID":
                    c_employeeDto=o_employeeService.findById(data);
                    break;
                default:
                    c_employeeDto=o_employeeService.findAll();
                    break;
            }
        }else{
            c_employeeDto=o_employeeService.findAll();
        }

        model.addAttribute("employees",c_employeeDto);
        return "/admin/employee/list";
    }
}
