package com.example.demo.Configuration;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static com.example.demo.Security.ApplicationRole.*;

@Configuration
public class UserConfiguration implements CommandLineRunner {

    private PasswordEncoder o_passwordEncoder;
    private UserRepository o_userRepository;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.o_passwordEncoder = passwordEncoder;
    }

    @Autowired
    public UserConfiguration(UserRepository o_userRepository) {
        this.o_userRepository = o_userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User(
                "admin",
                o_passwordEncoder.encode("admin"),
                "admin",
                "admin@gmail.com",
                "0943272522",
                "245 Wall Street",
                LocalDate.of(1991,9,23)
        );
        admin.setAuthorities(ADMIN.getAuthorities());
        User staff = new User(
                "staff",
                o_passwordEncoder.encode("staff"),
                "staff",
                "staff@gmail.com",
                "0943272511",
                "245 Wall Street",
                LocalDate.of(1991,9,23)
        );

        staff.setAuthorities(STAFF.getAuthorities());
        User student = new User(
                "student",
                o_passwordEncoder.encode("student"),
                "student",
                "student@gmail.com",
                "0943272500",
                "245 Wall Street",
                LocalDate.of(1991,9,23)
        );
        student.setAuthorities(STUDENT.getAuthorities());
        o_userRepository.save(admin);
        o_userRepository.save(staff);
        o_userRepository.save(student);
    }
}
