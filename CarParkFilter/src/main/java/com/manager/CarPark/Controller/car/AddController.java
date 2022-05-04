package com.manager.CarPark.Controller.car;

import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.ParkingLotDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Entity.Car;
import com.manager.CarPark.Entity.Trip;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.CarService;
import com.manager.CarPark.Service.ParkingLotService;
import com.manager.CarPark.Service.TripService;
import com.manager.CarPark.Util.Converter;
import com.manager.CarPark.Util.Validator.CarValidator;
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
import java.util.List;
import java.util.Optional;

@Controller("CarAddController")
@RequestMapping("/employee_dashboard/car/add")
public class AddController {
    @Autowired
    private CarService o_carService;

    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private CarValidator o_validator;

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


        List<ParkingLotDto> c_parkingLot = o_parkingLotService.findByStatus("EMPTY");
        model.addAttribute("parkingLots",c_parkingLot);
        model.addAttribute("car",new CarDto());
        return "/employee/car/add";
    }

    @PostMapping
    public String handleAdd(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            CarDto o_carDto
    ){
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);


        HashMap<String,Boolean> c_validToken = o_validator.validate(o_carDto);

        if(o_validator.isValid(c_validToken)){
            Car o_car = o_converter.DtoToCar(o_carDto);
            Optional<CarDto> o_carDB = o_carService.save(o_car);

            if(o_carDB.isPresent()){
                model.addAttribute("notification","Add Car Success");
            }else{
                model.addAttribute("notification","Add Car Fail");
            }
            return "/employee/car/notification";
        }else{

            List<ParkingLotDto> c_parkingLot = o_parkingLotService.findByStatus("EMPTY");
            model.addAttribute("parkingLots",c_parkingLot);
            model.addAttribute("car",o_carDto);
            c_validToken.forEach(model::addAttribute);
            return "/employee/car/add";
        }
    }

}
