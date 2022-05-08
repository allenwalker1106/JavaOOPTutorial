package com.example.demo.Util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface Validator {
    boolean validateDate(String date);
    boolean validateDate(LocalDate date);
    boolean validateTime(String time);
    boolean isValid(HashMap<String,Boolean> c_token);
    boolean isValid(List<HashMap<String,Boolean>> c_token);
    boolean validateName(String name);
    boolean validateEmail(String email);
    boolean validatePhoneNumber(String phoneNumber);
    boolean validatePassword(String password);
    boolean notEmpty(String data);
    boolean isPositive(Long number);
}
