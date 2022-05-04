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
public class AuthenticationFilter extends UrlFilter implements Filter {
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
        if(matchAll(o_httpRequest,"^\\/(admin_dashboard|employee_dashboard|dashboard\\/)\\/?.*")){
            if(!o_authenticateService.isAuthenticated(c_cookie)){
                o_httpResponse.sendRedirect("/login");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
