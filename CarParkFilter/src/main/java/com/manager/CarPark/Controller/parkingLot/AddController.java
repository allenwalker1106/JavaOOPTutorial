package com.manager.CarPark.Controller.parkingLot;


import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.ParkingLotDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Entity.ParkingLot;
import com.manager.CarPark.Entity.Trip;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.ParkingLotService;
import com.manager.CarPark.Util.Converter;
import com.manager.CarPark.Util.Validator.ParkingLotValidator;
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


@Controller("ParkingLotAddController")
@RequestMapping("/employee_dashboard/parkingLot/add")
public class AddController {
    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private ParkingLotValidator o_validator;

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
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);



        model.addAttribute("parkingLot",new ParkingLotDto());
        return "/employee/parkingLot/add";
    }

    @PostMapping
    public String handleAdd(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            ParkingLotDto o_parkingLotDto
    ){
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);


        HashMap<String,Boolean> c_validToken = o_validator.validate(o_parkingLotDto);

        if(o_validator.isValid(c_validToken)){
            ParkingLot o_parkingLot = o_converter.dtoToParkingLot(o_parkingLotDto);
            Optional<ParkingLotDto> o_parkingLotDB = o_parkingLotService.save(o_parkingLot);

            if(o_parkingLotDB.isPresent()){
                model.addAttribute("notification","Add Parking Lot Success");
            }else{
                model.addAttribute("notification","Add Parking Lot Fail");
            }
            return "/employee/parkingLot/notification";
        }else{
            model.addAttribute("parkingLot",o_parkingLotDto);
            c_validToken.forEach(model::addAttribute);
            return "/employee/parkingLot/add";
        }
    }
}
