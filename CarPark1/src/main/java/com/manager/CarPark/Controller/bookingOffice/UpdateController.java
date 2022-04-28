package com.manager.CarPark.Controller.bookingOffice;


import com.manager.CarPark.DTO.BookingOfficeDto;
import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.BookingOfficeService;
import com.manager.CarPark.Util.Validator.BookingOfficeValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller("BookingOfficeUpdateController")
@RequestMapping("/employee_dashboard/bookingOffice/list/view/update")
public class UpdateController {

    @Autowired
    private BookingOfficeService o_bookingOfficeService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private BookingOfficeValidator o_validator;

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


        List<BookingOfficeDto> c_bookingOfficeDto = o_bookingOfficeService.findById(id);
        if(c_bookingOfficeDto.size()!=0){
            BookingOfficeDto o_bookingOfficeDto = c_bookingOfficeDto.get(0);
            model.addAttribute("bookingOffice",o_bookingOfficeDto);
            return "/employee/bookingOffice/update";
        }
        return "redirect:/employee_dashboard/bookingOffice/list";
    }

    @PostMapping
    public String handleUpdate(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            BookingOfficeDto o_bookingOfficeDto
    ) {
        CredentialDto o_credential = new CredentialDto(ACCOUNT, ROLE, JSESSIONID, ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized = o_authenticationService.isAuthorized(o_credential, "EMPLOYEE");
        if (!b_isAuthenticated)
            return "redirect:/login";
        else if (!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account", ACCOUNT);

        HashMap<String, Boolean> c_validToken = o_validator.validateUpdate(o_bookingOfficeDto);
        if (o_validator.isValid(c_validToken)) {

            Optional<BookingOfficeDto> o_bookingOfficeDtoOp = o_bookingOfficeService.update(o_bookingOfficeDto);
            if (o_bookingOfficeDtoOp.isPresent()) {
                model.addAttribute("notification","Update Booking Office Success");
            }else{
                model.addAttribute("notification","Update Booking Office Fail");
            }
            return "/employee/bookingOffice/notification";
        } else {
            List<BookingOfficeDto> c_bookingOfficeDto = o_bookingOfficeService.findById(o_bookingOfficeDto.getId().toString());
            if(c_bookingOfficeDto.size()!=0) {
                o_bookingOfficeDto.setTrip(c_bookingOfficeDto.get(0).getTrip());
                model.addAttribute("bookingOffice", o_bookingOfficeDto);
                c_validToken.forEach(model::addAttribute);
                return "/employee/bookingOffice/update";
            }
            return "redirect:/employee_dashboard/bookingOffice/list";
        }
    }
}
