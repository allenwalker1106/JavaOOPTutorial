package com.manager.CarPark.Controller;

import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }


    @GetMapping("/dashboard")
    public String dashboard(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            HttpServletResponse o_response,
            Model model
    ){
            if(ROLE.equalsIgnoreCase("EMPLOYEE"))
                return "redirect:/employee_dashboard";
            else if(ROLE.equalsIgnoreCase("ADMIN"))
                return "redirect:/admin_dashboard";
            else
                return "redirect:/access_denied";

    }

    @GetMapping("/employee_dashboard")
    public String employeeDashboard(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            Model model
    ){
        model.addAttribute("account",ACCOUNT);
        return "/employee/dashboard";
    }

    @GetMapping("/admin_dashboard")
    public String adminDashboard(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            Model model
    ){
        model.addAttribute("account",ACCOUNT);
        return "/admin/dashboard";
    }
}
