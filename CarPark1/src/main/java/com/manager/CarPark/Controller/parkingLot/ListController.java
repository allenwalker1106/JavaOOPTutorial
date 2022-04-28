package com.manager.CarPark.Controller.parkingLot;


import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.ParkingLotDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.ParkingLotService;
import com.manager.CarPark.Service.TripService;
import com.manager.CarPark.Util.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("ParkingLotListController")
@RequestMapping("/employee_dashboard/parkingLot/list")
public class ListController {

    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private ModelMapper o_modelMapper;

    @GetMapping
    public String getList(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String data,
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



        List<ParkingLotDto> c_parkingLostDto ;
        if(field!=null&&data!=null){
            switch(field.toUpperCase()){
                case "Name":
                    c_parkingLostDto=o_parkingLotService.findByName(data);
                    break;
                case "PLACE":
                    c_parkingLostDto=o_parkingLotService.findByPlace(data);
                    break;
                case "AREA":
                    c_parkingLostDto=o_parkingLotService.findByArea(data);
                    break;
                case "PRICE":
                    c_parkingLostDto=o_parkingLotService.findByPrice(data);
                    break;
                case "STATUS":
                    c_parkingLostDto=o_parkingLotService.findByStatus(data);
                    break;
                case "ID":
                    c_parkingLostDto=o_parkingLotService.findById(data);
                    break;
                default:
                    c_parkingLostDto=o_parkingLotService.findAll();
                    break;
            }
        }else{
            c_parkingLostDto=o_parkingLotService.findAll();
        }

        model.addAttribute("parkingLots",c_parkingLostDto);
        return "/employee/parkingLot/list";
    }
}
