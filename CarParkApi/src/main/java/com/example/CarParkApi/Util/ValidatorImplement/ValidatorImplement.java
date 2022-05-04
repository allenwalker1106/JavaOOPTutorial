package com.example.CarParkApi.Util.ValidatorImplement;


import com.example.CarParkApi.Util.Validator.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorImplement implements Validator {
    @Override
    public boolean validateDate(String date) {
        try{
            return this.checkDateValid(date);
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean validateDate(LocalDate date) {
        return date!=null;
    }

    @Override
    public boolean validateTime(String time) {
        try{
            return this.checkTimeValid(time);
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean isValid(HashMap<String, Boolean> c_token) {
        try{
            for(String key: c_token.keySet()){
                if(!c_token.get(key))
                    return false;
            }
            return true;
        }catch(NullPointerException ex){
            return false;
        }
    }

    @Override
    public boolean isValid(List<HashMap<String, Boolean>> c_tokenList) {
        for(HashMap<String, Boolean> c_token:c_tokenList){
            if(!isValid(c_token))
                return false;
        }
        return true;
    }

    @Override
    public boolean validateName(String name) {
        try{
            return this.checkNameValid(name);
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean validateEmail(String email) {
        try{
            return this.checkEmailValid(email);

        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        try{
            return this.checkPhoneNumberValid(phoneNumber);
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean validatePassword(String password) {
        try{
            return this.checkPasswordValid(password);
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean notEmpty(String data){
        try{
            return data.trim().length()!=0;
        }catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean isPositive(Long number) {
        try{
            return number>=0;
        }catch(NullPointerException e){
            return false;
        }
    }


    private boolean checkStringValidPattern(String str_line,String str_pattern){
        Pattern pattern = Pattern.compile(str_pattern);
        Matcher matcher = pattern.matcher(str_line);
        return matcher.find();
    }

    private boolean checkDateValid(String str_date) {
        String str_pattern = "^(\\d{4})[ \\/-](02)[ \\/-](0[1-9]|[1-2][0-9])$|^(\\d{4})[ \\/-](0[469]|11)[ \\/-](0[1-9]|[1-2][0-9]|30)$|^(\\d{4})[ \\/-](0[13578]|10|12)[ \\/-](0[1-9]|[1-2][0-9]|30|31)$";
        if(this.checkStringValidPattern(str_date,str_pattern)) {
            return true;
        }
        return false;
    }

    private boolean checkNameValid(String str_name) {
        if(str_name.trim().isBlank())
            return false;
        String str_pattern = "[^a-zA-Z\\s]";
        if(this.checkStringValidPattern(str_name,str_pattern)) {
            return false;
        }
        return true;
    }

    private boolean checkPhoneNumberValid(String str_phoneNumber)  {
        String str_pattern="\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})";
        if(this.checkStringValidPattern(str_phoneNumber,str_pattern)) {
            return true;
        }
        return false;
    }

    private boolean checkEmailValid(String str_email){
        String str_pattern ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if(this.checkStringValidPattern(str_email,str_pattern)) {
            return true;
        }
        return false;
    }

    private boolean checkAccountValid(String str_account){
        String str_pattern ="^[a-zA-Z]\\w+$";
        if(this.checkStringValidPattern(str_account,str_pattern)) {
            return true;
        }
        return false;
    }

    private boolean checkPasswordValid(String str_password){
        String str_pattern ="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$&*]).{6}";
        if(this.checkStringValidPattern(str_password,str_pattern)) {
            return true;
        }
        return false;
    }

    private boolean checkTimeValid(String str_date) {
        String str_pattern = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
        if(this.checkStringValidPattern(str_date,str_pattern)) {
            return true;
        }
        return false;
    }
}
