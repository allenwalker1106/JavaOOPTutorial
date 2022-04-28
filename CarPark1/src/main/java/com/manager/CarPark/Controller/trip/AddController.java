package com.manager.CarPark.Controller.trip;

import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Entity.Trip;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.TripService;
import com.manager.CarPark.Util.Converter;
import com.manager.CarPark.Util.Validator.TripValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Optional;

@Controller("TripAddController")
@RequestMapping("/employee_dashboard/trip/add")
public class AddController {

    @Autowired
    private TripService o_tripService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private TripValidator o_validator;

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
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);



        model.addAttribute("trip",new TripDto());
        return "/employee/trip/add";
    }

    @PostMapping
    public String handleAdd(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            TripDto o_tripDto
    ){
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);


        HashMap<String,Boolean> c_validToken = o_validator.validate(o_tripDto);

        if(o_validator.isValid(c_validToken)){
            Trip o_trip = o_converter.dtoToTrip(o_tripDto);
            o_trip.setDepartureDate(LocalDate.parse(o_tripDto.getDepartureDate().toString()));
            o_trip.setDepartureTime(LocalTime.parse(o_tripDto.getDepartureTime().toString()));
            Optional<TripDto> o_tripDB = o_tripService.save(o_trip);

            if(o_tripDB.isPresent()){
                model.addAttribute("notification","Add Trip Success");
            }else{
                model.addAttribute("notification","Add Trip Fail");
            }
            return "/employee/trip/notification";
        }else{
            model.addAttribute("trip",o_tripDto);
            c_validToken.forEach(model::addAttribute);
            return "/employee/trip/add";
        }
    }
}
