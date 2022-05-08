package com.example.demo.Security;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository o_userRepository;

    @Autowired
    public void setUserRepository(UserRepository o_userRepository) {
        this.o_userRepository = o_userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String str_username) throws UsernameNotFoundException {
        if(str_username != null){
            User o_user = o_userRepository.findByAccount(str_username);
            if(o_user!=null) {
                return new CustomUserDetail(o_user);
            }
        }
        throw new UsernameNotFoundException("User doesn't exist");
    }
}
