package com.manager.CarPark.Controller.bookingOffice;


import com.manager.CarPark.DTO.BookingOfficeDto;
import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.BookingOfficeService;
import com.manager.CarPark.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("BookingOfficeDeleteController")
@RequestMapping("/employee_dashboard/bookingOffice/list/view/delete")
public class DeleteController {

    @Autowired
    private BookingOfficeService o_bookingOfficeService;

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

        List<BookingOfficeDto> c_bookingOfficeDto = o_bookingOfficeService.findById(id);
        if(c_bookingOfficeDto.size()!=0){
            BookingOfficeDto o_bookingOfficeDto = c_bookingOfficeDto.get(0);
            if(o_bookingOfficeService.delete(o_bookingOfficeDto)){
                model.addAttribute("notification","Success Delete Booking Office");
            }else{
                model.addAttribute("notification","Fail To Delete Booking Office");
            }

        }else{
            model.addAttribute("notification","Booking Office Doesn't Exist!");
        }
        return "/employee/bookingOffice/notification";
    }
}
