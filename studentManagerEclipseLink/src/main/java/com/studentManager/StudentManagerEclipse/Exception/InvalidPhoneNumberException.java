package com.studentManager.StudentManagerEclipse.Exception;

public class InvalidPhoneNumberException extends Exception{
    public InvalidPhoneNumberException() {
        super("Invalid Phone Number: Phone number can't contain non-digit character");
    }
}
