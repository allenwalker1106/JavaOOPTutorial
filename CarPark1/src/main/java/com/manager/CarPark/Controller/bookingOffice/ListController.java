package com.manager.CarPark.Controller.bookingOffice;

import com.manager.CarPark.DTO.BookingOfficeDto;
import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.BookingOfficeService;
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

@Controller("BookingOfficeListController")
@RequestMapping("/employee_dashboard/bookingOffice/list")
public class ListController {

    @Autowired
    private BookingOfficeService o_bookingOfficeService;

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



        List<BookingOfficeDto> c_bookingOfficeDto ;
        if(field!=null&&data!=null){
            switch(field.toUpperCase()){
                case "NAME":
                    c_bookingOfficeDto = o_bookingOfficeService.findByName(data);
                    break;
                case "PHONE":
                    c_bookingOfficeDto = o_bookingOfficeService.findByPhone(data);
                    break;
                case "TRIP":
                    c_bookingOfficeDto = o_bookingOfficeService.findByTrip(data);
                    break;
                case "ID":
                    c_bookingOfficeDto =  o_bookingOfficeService.findById(data);
                    break;
                default:
                    c_bookingOfficeDto = o_bookingOfficeService.findAll();
                    break;
            }
        }else{
            c_bookingOfficeDto=o_bookingOfficeService.findAll();
        }
        model.addAttribute("bookingOffices",c_bookingOfficeDto);
        return "/employee/bookingOffice/list";
    }
}
