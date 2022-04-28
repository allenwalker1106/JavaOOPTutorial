package com.manager.CarPark.Controller.car;


import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.CarService;
import com.manager.CarPark.Service.TripService;
import com.manager.CarPark.Util.Validator.CarValidator;
import com.manager.CarPark.Util.Validator.TripValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller("CarUpdateController")
@RequestMapping("/employee_dashboard/car/list/view/update")
public class UpdateController {
    @Autowired
    private CarService o_carService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private CarValidator o_validator;

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
            return "/employee/car/update";
        }
        return "redirect:/employee_dashboard/car/list";
    }

    @PostMapping
    public String handleUpdate(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            CarDto o_carDto
    ) {
        CredentialDto o_credential = new CredentialDto(ACCOUNT, ROLE, JSESSIONID, ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized = o_authenticationService.isAuthorized(o_credential, "EMPLOYEE");
        if (!b_isAuthenticated)
            return "redirect:/login";
        else if (!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account", ACCOUNT);

        HashMap<String, Boolean> c_validToken = o_validator.validateUpdate(o_carDto);
        if (o_validator.isValid(c_validToken)) {
            Optional<CarDto> o_carDtoOP = o_carService.update(o_carDto);
            if (o_carDtoOP.isPresent()) {
                model.addAttribute("notification","Update Car Success");
            }else{
                model.addAttribute("notification","Update Car Fail");
            }
            return "/employee/car/notification";
        } else {
            model.addAttribute("car", o_carDto);
            c_validToken.forEach(model::addAttribute);
            return "/employee/car/update";
        }
    }

}
