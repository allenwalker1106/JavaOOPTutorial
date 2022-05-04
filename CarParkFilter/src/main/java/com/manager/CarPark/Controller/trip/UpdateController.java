package com.manager.CarPark.Controller.trip;


import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.TripService;
import com.manager.CarPark.Util.Validator.TripValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller("TripUpdateController")
@RequestMapping("/employee_dashboard/trip/list/view/update")
public class UpdateController {

    @Autowired
    private TripService o_tripService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private TripValidator o_validator;


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


        List<TripDto> c_tripDto = o_tripService.findById(id);
        if(c_tripDto.size()!=0){
            TripDto o_tripDto = c_tripDto.get(0);
            model.addAttribute("trip",o_tripDto);
            return "/employee/trip/update";
        }
        return "redirect:/employee_dashboard/trip/list";
    }

    @PostMapping
    public String handleUpdate(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            TripDto o_tripDto
    ) {
        CredentialDto o_credential = new CredentialDto(ACCOUNT, ROLE, JSESSIONID, ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized = o_authenticationService.isAuthorized(o_credential, "EMPLOYEE");
        if (!b_isAuthenticated)
            return "redirect:/login";
        else if (!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account", ACCOUNT);

        HashMap<String, Boolean> c_validToken = o_validator.validateUpdate(o_tripDto);
        if (o_validator.isValid(c_validToken)) {

            Optional<TripDto> o_tripDtoOp = o_tripService.update(o_tripDto);
            if (o_tripDtoOp.isPresent()) {
                model.addAttribute("notification","Update Trip Success");
            }else{
                model.addAttribute("notification","Update Trip Fail");
            }
            return "/employee/trip/notification";
        } else {
            model.addAttribute("trip", o_tripDto);
            c_validToken.forEach(model::addAttribute);
            return "/employee/trip/update";
        }
    }

}
