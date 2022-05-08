package com.example.demo.Util;


import com.example.demo.DTO.UserDto;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class UserValidatorImplement extends ValidatorImplement implements UserValidator {

    private UserRepository o_userRepository;

    @Autowired
    public void setUserRepository(UserRepository o_userRepository) {
        this.o_userRepository = o_userRepository;
    }

    @Override
    public boolean validateAccount(String account) {
        if(notEmpty(account)){
            return o_userRepository.findByAccount(account)==null;
        }
        return false;
    }

    @Override
    public boolean validateRole(String role) {
        if(notEmpty(role)){
            switch(role.toUpperCase()){
                case "ADMIN":
                case "STAFF":
                case "STUDENT":
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }


    @Override
    public boolean validateEmail(String email) {
        if(super.validateEmail(email)){
            return o_userRepository.findByEmail(email)==null;
        }
        return false;
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        if(super.validatePhoneNumber(phoneNumber)){
            return o_userRepository.findByPhone(phoneNumber)==null;
        }
        return false;
    }

    @Override
    public HashMap<String, Boolean> validate(UserDto o_user) {
        HashMap<String, Boolean> c_token = new HashMap<>();
        c_token.put("isAccountValid",this.validateAccount(o_user.getAccount()));
        c_token.put("isPasswordValid",this.validatePassword(o_user.getPassword()));
        c_token.put("isNameValid",this.validateName(o_user.getName()));
        c_token.put("isEmailValid",this.validateEmail(o_user.getEmail()));
        c_token.put("isPhoneNumberValid",this.validatePhoneNumber(o_user.getPhone()));
        c_token.put("isAddressValid",this.notEmpty(o_user.getAddress()));
        c_token.put("isRoleValid",this.validateRole(o_user.getRole()));
        return c_token;
    }

}
