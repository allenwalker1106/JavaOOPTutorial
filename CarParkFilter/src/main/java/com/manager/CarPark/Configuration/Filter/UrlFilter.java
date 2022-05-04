package com.manager.CarPark.Configuration.Filter;

import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFilter {
    public boolean matchAll(HttpServletRequest o_request, String path){
        String uri = o_request.getRequestURI();
        return matchStringPattern(uri,path);
    }

    public boolean match(HttpServletRequest o_request, String path){
        String uri = o_request.getRequestURI();
        return uri.equals(path);
    }

    private boolean matchStringPattern(String str_line,String str_pattern){
        Pattern pattern = Pattern.compile(str_pattern);
        Matcher matcher = pattern.matcher(str_line);
        return matcher.find();
    }

    public HashMap<String,Cookie> toCookieMap(List<Cookie> c_cookies){
        HashMap<String,Cookie> c_cookieMap = new HashMap<>();
        c_cookies.forEach(cookie->c_cookieMap.put(cookie.getName(),cookie));
        return c_cookieMap;
    }

    public String getSessionId(){
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}
