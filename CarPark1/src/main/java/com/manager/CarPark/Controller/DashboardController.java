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
    @Autowired
    private AuthenticationService o_authenticationService;

    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }


    @GetMapping("/dashboard")
    public String dashboard(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            HttpServletResponse o_response,
            Model model
    ){
        Cookie sessionID= o_authenticationService.getInitCookie();
        o_response.addCookie(sessionID);
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        if(b_isAuthenticated){
            if(o_authenticationService.isAuthorized(o_credential,"EMPLOYEE"))
                return "redirect:/employee_dashboard";
            else if(o_authenticationService.isAuthorized(o_credential,"ADMIN"))
                return "redirect:/admin_dashboard";
            else
                return "redirect:/access_denied";
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping("/employee_dashboard")
    public String employeeDashboard(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            HttpServletResponse o_response,
            Model model
    ){

        Cookie sessionID= o_authenticationService.getInitCookie();
        o_response.addCookie(sessionID);
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");

        if(!b_isAuthenticated){
            return "redirect:/login";
        }else if(!b_isAuthorized){
            return "redirect:/access_denied";
        }
        model.addAttribute("account",ACCOUNT);
        return "/employee/dashboard";
    }

    @GetMapping("/admin_dashboard")
    public String adminDashboard(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            HttpServletResponse o_response,
            Model model
    ){

        Cookie sessionID= o_authenticationService.getInitCookie();
        o_response.addCookie(sessionID);
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"ADMIN");

        if(!b_isAuthenticated){
            return "redirect:/login";
        }else if(!b_isAuthorized){
            return "redirect:/access_denied";
        }
        model.addAttribute("account",ACCOUNT);
        return "/admin/dashboard";
    }
}
