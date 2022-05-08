package com.example.demo.Util;

import com.example.demo.DTO.UserDto;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface UserValidator extends Validator {
    boolean validateAccount(String account);
    boolean validateRole(String role);
    HashMap<String,Boolean> validate(UserDto o_user);
}
