package com.manager.CarPark.Configuration.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class SessionFilter extends UrlFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest o_httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse o_httpResponse = (HttpServletResponse) servletResponse;
        if(matchAll(o_httpRequest,"^\\/.*")){
            try{
                HashMap<String,Cookie> c_cookie = toCookieMap(List.of(o_httpRequest.getCookies()));
                if(!c_cookie.containsKey("JSESSIONID")||!getSessionId().equals(c_cookie.get("JSESSIONID").getValue())){
                    o_httpResponse.addCookie(getSessionCookie());
                    o_httpResponse.sendRedirect("/login");
                }
            }catch(NullPointerException e){
                o_httpResponse.addCookie(getSessionCookie());
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public Cookie getSessionCookie(){
        Cookie cookie = new Cookie("JSESSIONID",RequestContextHolder.currentRequestAttributes().getSessionId());
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60*60);
        return cookie;
    }
}
