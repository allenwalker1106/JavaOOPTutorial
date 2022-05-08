package com.example.demo.jwt;

import com.example.demo.DTO.Credential;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authManager;


    @Autowired
    public JwtAuthenticateFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try{
            Credential o_credential = new ObjectMapper().readValue(request.getInputStream(), Credential.class);
//            System.out.println(o_credential);
            Authentication authentication =new UsernamePasswordAuthenticationToken(
                    o_credential.getUsername(),
                    o_credential.getPassword()
            );

            Authentication o_authenticate = authManager.authenticate(authentication);
//            System.out.println(o_authenticate);
            return o_authenticate;
        }catch(IOException e){
            throw new RuntimeException("credential fail");
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key = "a34c3d45b6018d3fd5560b103c2a00e2";
        String str_token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2L)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();

        response.addHeader("Authorization","Bearer "+str_token);
    }
}
