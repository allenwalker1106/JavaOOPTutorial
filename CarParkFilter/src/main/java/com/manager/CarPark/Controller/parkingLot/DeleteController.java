package com.manager.CarPark.Controller.parkingLot;


import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.ParkingLotDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.CarService;
import com.manager.CarPark.Service.ParkingLotService;
import com.manager.CarPark.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("ParkingLotDeleteController")
@RequestMapping("/employee_dashboard/parkingLot/list/view/delete")
public class DeleteController {

    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private CarService o_carService;

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
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
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
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);

        List<ParkingLotDto> c_parkingLotDto = o_parkingLotService.findById(id);
        if(c_parkingLotDto.size()!=0){
            ParkingLotDto o_parkingLotDto = c_parkingLotDto.get(0);
            if(o_parkingLotService.delete(o_parkingLotDto)){
                model.addAttribute("notification","Success Delete Parking Lot");
            }else{
                model.addAttribute("notification","Fail To Delete Parking Lot");
            }

        }else{
            model.addAttribute("notification","Parking Lot Doesn't Exist!");
        }
        return "/employee/parkingLot/notification";
    }

}
