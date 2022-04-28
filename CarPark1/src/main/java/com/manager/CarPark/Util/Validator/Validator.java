package com.manager.CarPark.Util.Validator;

import java.util.HashMap;

public interface Validator {
    boolean validateDate(String date);
    boolean validateTime(String time);
    boolean isValid(HashMap<String,Boolean> c_token);
    boolean validateName(String name);
    boolean validateEmail(String email);
    boolean validatePhoneNumber(String phoneNumber);
    boolean validatePassword(String password);
    boolean notEmpty(String data);
    boolean isPositive(Long number);
}
