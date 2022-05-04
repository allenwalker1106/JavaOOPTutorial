package com.manager.CarPark.Configuration.Filter;

import com.manager.CarPark.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class AuthorizationFilter extends UrlFilter implements Filter {

    @Autowired
    private AuthenticationService o_authenticateService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest o_httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse o_httpResponse = (HttpServletResponse) servletResponse;
        HashMap<String, Cookie> c_cookie = toCookieMap(List.of(o_httpRequest.getCookies()));
        if(matchAll(o_httpRequest,"^\\/(admin_dashboard)\\/?.*")){
            if(!o_authenticateService.isAuthorized(c_cookie,"ADMIN")){
                o_httpResponse.sendRedirect("/access_denied");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else if (matchAll(o_httpRequest,"^\\/(employee_dashboard)\\/?.*")){
            if(!o_authenticateService.isAuthorized(c_cookie,"EMPLOYEE")){
                o_httpResponse.sendRedirect("/access_denied");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
