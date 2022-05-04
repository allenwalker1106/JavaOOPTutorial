package com.manager.CarPark.Service;


import com.manager.CarPark.DTO.CredentialDto;
import com.manager.CarPark.Entity.Employee;
import com.manager.CarPark.Entity.Permission;
import com.manager.CarPark.Repository.EmployeeRepository;
import com.manager.CarPark.Repository.PermissionRepository;
import com.manager.CarPark.Util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private EmployeeRepository o_employeeRepository;

    @Autowired
    private PermissionRepository o_permissionRepository;

    @Autowired
    private Encoder o_securityService;


    public boolean isAuthenticated(CredentialDto o_credential){
        if(
            o_credential.getAccount()==null||
            o_credential.getRole()==null||
            o_credential.getSessionId()==null||
            o_credential.getAccessKey()==null
        ){
            return false;
        }else{

            List<Employee> c_employee = o_employeeRepository.findByAccount(o_credential.getAccount());
            if(c_employee.size()==0){
                return false;
            }else{
                Employee o_employee = c_employee.get(0);
                Permission o_permission = o_employee.getPermission();
                if(!o_credential.getSessionId().equals(RequestContextHolder.currentRequestAttributes().getSessionId()))
                    return false;
                if(!o_credential.getRole().equalsIgnoreCase(o_permission.getRole()))
                    return false;
                if(!o_credential.getSessionId().equals(o_permission.getSessionId()))
                    return false;
                if(!o_securityService.validateHash(o_securityService.applyMd5(o_credential.getSessionId()+o_credential.getAccount()),o_permission.getAccessKey()))
                    return false;
                if(!o_securityService.validateHash(o_securityService.applyMd5(o_credential.getSessionId()+o_credential.getAccount()),o_credential.getAccessKey()))
                    return false;
            }
        }
        return true;
    }

    public boolean isAuthenticated(HashMap<String, Cookie> c_cookie){
        try{
            if(     c_cookie.containsKey("JSESSIONID")&&
                    c_cookie.containsKey("ACCOUNT")&&
                    c_cookie.containsKey("ACCESS_TOKEN")&&
                    c_cookie.containsKey("ROLE")
            ){
                CredentialDto o_credentialDto = new CredentialDto(
                        c_cookie.get("ACCOUNT").getValue(),
                        c_cookie.get("ROLE").getValue(),
                        c_cookie.get("JSESSIONID").getValue(),
                        c_cookie.get("ACCESS_TOKEN").getValue()
                );
                return isAuthenticated(o_credentialDto);
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAuthorized(HashMap<String, Cookie> c_cookie,String role){
        try{
            if(     c_cookie.containsKey("JSESSIONID")&&
                    c_cookie.containsKey("ACCOUNT")&&
                    c_cookie.containsKey("ACCESS_TOKEN")&&
                    c_cookie.containsKey("ROLE")
            ){
                CredentialDto o_credentialDto = new CredentialDto(
                        c_cookie.get("ACCOUNT").getValue(),
                        c_cookie.get("ROLE").getValue(),
                        c_cookie.get("JSESSIONID").getValue(),
                        c_cookie.get("ACCESS_TOKEN").getValue()
                );
                return isAuthorized(o_credentialDto,role);
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAuthorized(CredentialDto o_credential,String str_role){
        List<Employee> c_employee = o_employeeRepository.findByAccount(o_credential.getAccount());
        if(c_employee.size()==0){
            return false;
        }else {
            Employee o_employee = c_employee.get(0);
            Permission o_permission = o_employee.getPermission();
            if(
                    o_credential.getRole().equalsIgnoreCase(o_permission.getRole())&&
                    o_credential.getRole().equalsIgnoreCase(str_role)
            )
                return true;
            else
                return false;
        }
    }

    public CredentialDto authenticate(CredentialDto o_credential){
        if(
            o_credential.getAccount()==null||
            o_credential.getSessionId()==null||
            o_credential.getPassword()==null
        ){
            return o_credential;
        }else{
            List<Employee> c_employee = o_employeeRepository.findByAccount(o_credential.getAccount());
            if(c_employee.size()==0){
                return o_credential;
            }else {
                Employee o_employee = c_employee.get(0);
                Permission o_permission = o_employee.getPermission();
                String str_password = o_securityService.applyMd5(o_credential.getPassword());
                String db_password =o_employee.getPassword();
                if(!o_securityService.validateHash(str_password,db_password))
                    return o_credential;
                else{
                    String str_accessKey = o_securityService.encrypt(o_credential.getSessionId()+o_employee.getAccount());
                    o_permission.setSessionId(o_credential.getSessionId());
                    o_permission.setAccessKey(str_accessKey);
                    o_permissionRepository.save(o_permission);

                    CredentialDto o_credentialDto = new CredentialDto();
                    o_credentialDto.setAccount(o_employee.getAccount());
                    o_credentialDto.setSessionId(o_permission.getSessionId());
                    o_credentialDto.setAccessKey(o_permission.getAccessKey());
                    o_credentialDto.setRole(o_permission.getRole());
                    return o_credentialDto;
                }
            }
        }
    }

    public boolean unAuthenticate(CredentialDto o_credential){
        if(!this.isAuthenticated(o_credential))
            return true;
        else{
            if(
                    o_credential.getAccount()==null||
                    o_credential.getRole()==null||
                    o_credential.getSessionId()==null||
                    o_credential.getAccessKey()==null
            ){
                return false;
            }else{
                List<Employee> c_employee = o_employeeRepository.findByAccount(o_credential.getAccount());
                if(c_employee.size()==0){
                    return false;
                }else{
                    Employee o_employee = c_employee.get(0);
                    Permission o_permission = o_employee.getPermission();
                    o_permission.setAccessKey(null);
                    o_permission.setSessionId(null);
                    o_permissionRepository.save(o_permission);
                    return true;
                }
            }
        }
    }

    public Cookie getInitCookie(){
        Cookie cookie = new Cookie("JSESSIONID",RequestContextHolder.currentRequestAttributes().getSessionId());
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60*60);
        return cookie;
    }

    public Cookie removeCookie(){
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    public CredentialDto getCredential(List<Cookie> cookies){
        CredentialDto o_credential = new CredentialDto();
        for(Cookie cookie: cookies){
            if(cookie.getName().equalsIgnoreCase("JSESSIONID"))
                o_credential.setSessionId(cookie.getValue());
            else if(cookie.getName().equalsIgnoreCase("ACCOUNT"))
                o_credential.setAccount(cookie.getValue());
            else if(cookie.getName().equalsIgnoreCase("ACCESS_TOKEN"))
                o_credential.setAccessKey(cookie.getValue());
        }
        return o_credential;
    }

    public Cookie getAccessTokenCookie(String accessToken){
        Cookie cookie = new Cookie("ACCESS_TOKEN",accessToken);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60*60);
        return cookie;
    }

    public Cookie getAccountCookie(String userAccount){
        Cookie cookie = new Cookie("ACCOUNT",userAccount);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60*60);
        return cookie;
    }

    public Cookie getRoleCookie(String role){
        Cookie cookie = new Cookie("ROLE",role);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60*60);
        return cookie;
    }

    public List<Cookie> credentialDtoToCookies(CredentialDto credentials){
        System.out.println(credentials);
        List<Cookie> c_cookies = new ArrayList<>();
        c_cookies.add(getInitCookie());
        c_cookies.add(getAccessTokenCookie(credentials.getAccessKey()));
        c_cookies.add(getAccountCookie(credentials.getAccount()));
        c_cookies.add(getRoleCookie(credentials.getRole()));

        return c_cookies;
    }
    public List<Cookie> clearCookieList(){
        Cookie access = new Cookie("ACCESS_TOKEN","");
        access.setMaxAge(0);
        Cookie account = new Cookie("ACCOUNT","");
        account.setMaxAge(0);
        Cookie role = new Cookie("ROLE","");
        role.setMaxAge(0);
        return List.of(access,account,role);

    }
}
