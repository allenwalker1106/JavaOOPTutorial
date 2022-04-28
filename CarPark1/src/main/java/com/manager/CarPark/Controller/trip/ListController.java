package com.manager.CarPark.Controller.trip;

import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
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

@Controller("TripListController")
@RequestMapping("/employee_dashboard/trip/list")
public class ListController {

    @Autowired
    private TripService o_tripService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private Converter o_converter;


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



        List<TripDto> c_tripDto ;
        if(field!=null&&data!=null){
            switch(field.toUpperCase()){
                case "DRIVER":
                    c_tripDto=o_tripService.findByDriver(data);
                    break;
                case "DESTINATION":
                    c_tripDto=o_tripService.findByDestination(data);
                    break;
                case "DATE":
                    c_tripDto=o_tripService.findByDate(data);
                    break;
                case "ID":
                    c_tripDto=o_tripService.findById(data);
                    break;
                case "TIME":
                    c_tripDto=o_tripService.findByTime(data);
                    break;
                default:
                    c_tripDto=o_tripService.findAll();
                    break;
            }
        }else{
            c_tripDto=o_tripService.findAll();
        }

        model.addAttribute("trips",c_tripDto);
        return "/employee/trip/list";
    }
}
