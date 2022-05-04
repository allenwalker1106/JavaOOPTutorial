package com.manager.CarPark.Controller.parkingLot;


import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.ParkingLotDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.ParkingLotService;
import com.manager.CarPark.Service.TripService;
import com.manager.CarPark.Util.Validator.ParkingLotValidator;
import com.manager.CarPark.Util.Validator.TripValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller("ParkingLotUpdateController")
@RequestMapping("/employee_dashboard/parkingLot/list/view/update")
public class UpdateController {
    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private ParkingLotValidator o_validator;

    @Autowired
    private ModelMapper o_modelMapper;

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


        List<ParkingLotDto> c_parkingLotDto = o_parkingLotService.findById(id);
        if(c_parkingLotDto.size()!=0){
            ParkingLotDto o_parkingLotDto = c_parkingLotDto.get(0);
            model.addAttribute("parkingLot",o_parkingLotDto);
            return "/employee/parkingLot/update";
        }
        return "redirect:/employee_dashboard/parkingLot/list";
    }

    @PostMapping
    public String handleUpdate(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            ParkingLotDto o_parkingLotDto
    ) {
        CredentialDto o_credential = new CredentialDto(ACCOUNT, ROLE, JSESSIONID, ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized = o_authenticationService.isAuthorized(o_credential, "EMPLOYEE");
        if (!b_isAuthenticated)
            return "redirect:/login";
        else if (!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account", ACCOUNT);

        HashMap<String, Boolean> c_validToken = o_validator.validateUpdate(o_parkingLotDto);
        if (o_validator.isValid(c_validToken)) {

            Optional<ParkingLotDto> o_parkingLotDtoOp = o_parkingLotService.update(o_parkingLotDto);
            if (o_parkingLotDtoOp.isPresent()) {
                model.addAttribute("notification","Update Parking Lot Success");
            }else{
                model.addAttribute("notification","Update Parking Lot Fail");
            }
            return "/employee/parkingLot/notification";
        } else {
            model.addAttribute("parkingLot", o_parkingLotDto);
            c_validToken.forEach(model::addAttribute);
            return "/employee/parkingLot/update";
        }
    }
}
