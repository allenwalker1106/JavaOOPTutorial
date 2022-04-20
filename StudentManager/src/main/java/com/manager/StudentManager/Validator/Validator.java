package com.manager.StudentManager.Validator;

import com.manager.StudentManager.Exception.InvalidDateException;
import com.manager.StudentManager.Exception.InvalidEmailFormatException;
import com.manager.StudentManager.Exception.InvalidNameException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {
    public Validator(){

    }

    public boolean checkNameValid(String str_name) throws InvalidNameException {
        String str_pattern = "[^a-zA-Z\\s]";
        if(this.checkStringValidPattern(str_name,str_pattern)) {
            throw  new InvalidNameException();
        }
        return true;
    }

    public boolean checkDateValid(String str_date) throws InvalidDateException {
        String str_pattern = "^(0[1-9]|[1-2][0-9])[ \\/-](02)[ \\/-](\\d{4})$|^(0[1-9]|[1-2][0-9]|30)[ \\/-](0[469]|11)[ \\/-](\\d{4})$|^(0[1-9]|[1-2][0-9]|30|31)[ \\/-](0[13578]|10|12)[ \\/-](\\d{4})$";
        if(this.checkStringValidPattern(str_date,str_pattern)) {
            return true;
        }
        throw  new InvalidDateException();
    }

    public boolean checkStringValidPattern(String str_line,String str_pattern){
        Pattern pattern = Pattern.compile(str_pattern);
        Matcher matcher = pattern.matcher(str_line);
        return matcher.find();
    }

    public boolean checkEmailValid(String str_email) throws InvalidEmailFormatException {
        String str_pattern ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if(this.checkStringValidPattern(str_email,str_pattern)) {
            return true;
        }
        throw  new InvalidEmailFormatException();
    }

}
