package com.manager.CarPark.Controller.ticket;


import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.DTO.TicketDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Service.AuthenticationService;
import com.manager.CarPark.Service.TicketService;
import com.manager.CarPark.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("TicketViewController")
@RequestMapping("/employee_dashboard/ticket/list/view")
public class ViewController {
    @Autowired
    private TicketService o_ticketService;

    @Autowired
    private AuthenticationService o_authenticationService;

    @GetMapping
    public String getView(
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

        List<TicketDto> c_ticketDto = o_ticketService.findById(id);
        if(c_ticketDto.size()!=0){
            TicketDto o_ticketDto = c_ticketDto.get(0);
            model.addAttribute("ticket",o_ticketDto);
            return "/employee/ticket/view";
        }

        return "redirect:/employee_dashboard/ticket/list";
    }
}
