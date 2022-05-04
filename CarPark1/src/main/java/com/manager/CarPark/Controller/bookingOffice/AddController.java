package com.manager.CarPark.Controller.bookingOffice;


import com.manager.CarPark.DTO.BookingOfficeDto;
import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Entity.BookingOffice;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.BookingOfficeService;
import com.manager.CarPark.Service.TripService;
import com.manager.CarPark.Util.Converter;
import com.manager.CarPark.Util.Validator.BookingOfficeValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller("BookingOfficeAddController")
@RequestMapping("/employee_dashboard/bookingOffice/add")
public class AddController {
    @Autowired
    private BookingOfficeService o_bookingOfficeService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private BookingOfficeValidator o_validator;

    @Autowired
    private TripService o_tripService;

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



        List<TripDto> c_trips = o_tripService.findAll();
        model.addAttribute("trips",c_trips);
        model.addAttribute("bookingOffice",new BookingOfficeDto());
        return "/employee/bookingOffice/add";
    }

    @PostMapping
    public String handleAdd(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            @RequestBody  BookingOfficeDto o_bookingOfficeDto
    ){
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);


        HashMap<String,Boolean> c_validToken = o_validator.validate(o_bookingOfficeDto);

        if(o_validator.isValid(c_validToken)){
            BookingOffice o_bookingOffice = o_converter.DtoToBookingOffice(o_bookingOfficeDto);
            o_bookingOffice.setStartDate(LocalDate.parse(o_bookingOfficeDto.getStartDate()));
            o_bookingOffice.setEndDate(LocalDate.parse(o_bookingOfficeDto.getEndDate()));
            Optional<BookingOfficeDto> o_bookingOfficeDB = o_bookingOfficeService.save(o_bookingOffice);

            if(o_bookingOfficeDB.isPresent()){
                model.addAttribute("notification","Add Booking Office Success");
            }else{
                model.addAttribute("notification","Add Booking Office Fail");
            }
            return "/employee/bookingOffice/notification";
        }else{
            List<TripDto> c_trips = o_tripService.findAll();
            model.addAttribute("trips",c_trips);
            model.addAttribute("bookingOffice",o_bookingOfficeDto);
            c_validToken.forEach(model::addAttribute);
            return "/employee/bookingOffice/add";
        }
    }
}
