package com.manager.CarPark.Controller.employee;

import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.EmployeeDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller("EmployeeDeleteController")
@RequestMapping("/admin_dashboard/employee/list/view/delete")
public class DeleteController {

    @Autowired
    private EmployeeService o_employeeService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @GetMapping
    public String getDelete(
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

        return "redirect:/dashboard";
    }

    @PostMapping
    public String handleDelete(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            @RequestParam(required = false) String id
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
            if(o_employeeDto.getPermission().getRole().equalsIgnoreCase("ADMIN")){
                model.addAttribute("notification","Invalid Delete Request Can't Delete Admin From The System");
            }else{
                if(o_employeeService.delete(o_employeeDto)){
                    model.addAttribute("notification","Success Delete Employee");
                }else{
                    model.addAttribute("notification","Fail To Delete Employee");
                }
            }
        }else{
            model.addAttribute("notification","Employee Doesn't Exist!");
        }
        return "/admin/employee/notification";
    }
}
