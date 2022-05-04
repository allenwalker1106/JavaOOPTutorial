package com.manager.CarPark.Controller;

import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CredentialController {

    @Autowired
    private AuthenticationService o_authenticationService;

    @GetMapping("/login")
    public String showLoginForm(
            Model model
    ){
        model.addAttribute("credential",new CredentialDto());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            HttpServletResponse o_response,
            CredentialDto o_credential,
            Model model
    ) {
        Cookie sessionID= o_authenticationService.getInitCookie();
        o_response.addCookie(sessionID);
        o_credential.setSessionId(sessionID.getValue());
        CredentialDto  o_credentialDto= o_authenticationService.authenticate(o_credential);
        if(o_credentialDto.getAccessKey()==null){
            model.addAttribute("credential",o_credential);
            model.addAttribute("isValid",false);
            return "login";
        }else{
            List<Cookie> c_cookies = o_authenticationService.credentialDtoToCookies(o_credentialDto);
            for(Cookie cookie:c_cookies){
                o_response.addCookie(cookie);
            }
            return "redirect:/dashboard";
        }
    }

    @GetMapping("/logout")
    public String logOut(
            @CookieValue(value="ACCOUNT", required = false) String ACCOUNT,
            @CookieValue(value="ROLE", required = false) String ROLE,
            @CookieValue(value="JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value="ACCESS_TOKEN", required = false) String ACCESS_TOKEN,
            HttpServletResponse o_response
    ){
        Cookie sessionID= o_authenticationService.getInitCookie();
        o_response.addCookie(sessionID);
        if(sessionID.getValue().equals(JSESSIONID)){
            CredentialDto o_credential = new CredentialDto(ACCOUNT,ROLE,JSESSIONID,ACCESS_TOKEN);
            if(o_authenticationService.isAuthenticated(o_credential)){
                o_authenticationService.unAuthenticate(o_credential);
            }
        }
        List<Cookie> cookies = o_authenticationService.clearCookieList();
        for(Cookie cookie: cookies){
            o_response.addCookie(cookie);
        }
        return "redirect:/login";
    }
}
