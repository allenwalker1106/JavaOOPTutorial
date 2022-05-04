package com.manager.CarPark.Controller.car;


import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.CarService;
import com.manager.CarPark.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("CarViewController")
@RequestMapping("/employee_dashboard/car/list/view")
public class ViewController {

    @Autowired
    private CarService o_carService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @GetMapping
    public String getView(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            @RequestParam(required = false) String id,
            Model model
    ){
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);

        List<CarDto> c_carDto = o_carService.findById(id);
        if(c_carDto.size()!=0){
            CarDto o_carDto = c_carDto.get(0);
            model.addAttribute("car",o_carDto);
            return "/employee/car/view";
        }

        return "redirect:/employee_dashboard/car/list";
    }
}
