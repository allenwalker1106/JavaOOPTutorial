package com.example.demo.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String str_authorizationHeader = request.getHeader("Authorization");
        if(str_authorizationHeader==null||str_authorizationHeader.isEmpty()||!str_authorizationHeader.startsWith("Bearer ")){
            //invalid jwt token
            filterChain.doFilter(request,response);
            return;
        }else{
            String str_jwtToken = str_authorizationHeader.replace("Bearer ","");
            try {
                String key = "a34c3d45b6018d3fd5560b103c2a00e2";
                JwtParser o_jwtParser = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                        .build();

                Jws<Claims> o_claims = o_jwtParser.parseClaimsJws(str_jwtToken);

                Claims o_dataBody = o_claims.getBody();
                String str_userName = o_dataBody.getSubject();
                List<Map<String, String>> c_authorities = (List<Map<String, String>>) o_dataBody.get("authorities");
                Set<GrantedAuthority> c_grantedAuthorities = c_authorities.stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.get("authority")))
                        .collect(Collectors.toSet());

                Authentication o_auth = new UsernamePasswordAuthenticationToken(
                        str_userName,
                        null,
                        c_grantedAuthorities
                );

                SecurityContextHolder.getContext().setAuthentication(o_auth);

            }catch(JwtException e){
                //invalid jwt token
                throw new IllegalStateException("token invalid :"+str_jwtToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
