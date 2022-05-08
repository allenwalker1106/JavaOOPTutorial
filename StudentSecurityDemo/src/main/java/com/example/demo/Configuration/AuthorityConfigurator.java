package com.example.demo.Configuration;

import com.example.demo.Repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.Security.ApplicationRole.*;

@Configuration
public class AuthorityConfigurator implements CommandLineRunner {
    private AuthorityRepository o_authRepository;

    @Autowired
    public void setAuthRepository(AuthorityRepository o_authRepository) {
        this.o_authRepository = o_authRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        o_authRepository.saveAll(ADMIN.getAuthorities());
        o_authRepository.saveAll(STAFF.getAuthorities());
        o_authRepository.saveAll(STUDENT.getAuthorities());
    }
}
