package com.manager.CarPark.Controller.ticket;


import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TicketDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Entity.Ticket;
import com.manager.CarPark.Entity.Trip;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.CarService;
import com.manager.CarPark.Service.TicketService;
import com.manager.CarPark.Service.TripService;
import com.manager.CarPark.Util.Converter;
import com.manager.CarPark.Util.Validator.TicketValidator;
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

@Controller("TicketAddController")
@RequestMapping("/employee_dashboard/ticket/add")
public class AddController {
    @Autowired
    private TicketService o_ticketService;

    @Autowired
    private TripService o_tripService;

    @Autowired
    private CarService o_carService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @Autowired
    private Converter o_converter;

    @Autowired
    private TicketValidator o_validator;

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

        List<CarDto> c_cars = o_carService.findAll();
        model.addAttribute("cars",c_cars);

        model.addAttribute("ticket",new TicketDto());
        return "/employee/ticket/add";
    }

    @PostMapping
    public String handleAdd(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            Model model,
            TicketDto o_ticketDto
    ){
        CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
        boolean b_isAuthenticated = o_authenticationService.isAuthenticated(o_credential);
        boolean b_isAuthorized  = o_authenticationService.isAuthorized(o_credential,"EMPLOYEE");
        if(!b_isAuthenticated)
            return "redirect:/login";
        else if(!b_isAuthorized)
            return "redirect:/access_denied";
        model.addAttribute("account",ACCOUNT);


        HashMap<String,Boolean> c_validToken = o_validator.validate(o_ticketDto);

        if(o_validator.isValid(c_validToken)){
            Ticket o_ticket = o_converter.DtoToTicket(o_ticketDto);
            o_ticket.setTime(LocalTime.parse(o_ticketDto.getTime()));
            Optional<TicketDto> o_ticketDB = o_ticketService.save(o_ticket);

            if(o_ticketDB.isPresent()){
                model.addAttribute("notification","Add Ticket Success");
            }else{
                model.addAttribute("notification","Add Ticket Fail");
            }
            return "/employee/ticket/notification";
        }else{


            List<TripDto> c_trips = o_tripService.findAll();
            model.addAttribute("trips",c_trips);

            List<CarDto> c_cars = o_carService.findAll();
            model.addAttribute("cars",c_cars);

            model.addAttribute("ticket",o_ticketDto);
            c_validToken.forEach(model::addAttribute);
            return "/employee/ticket/add";
        }
    }

}
