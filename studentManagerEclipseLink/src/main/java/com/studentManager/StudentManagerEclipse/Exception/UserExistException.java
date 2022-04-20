package com.studentManager.StudentManagerEclipse.Exception;

public class UserExistException extends Exception{
    public UserExistException(){
        super("User Already Exist !");
    }
    public UserExistException(String errorMessage) {
        super(errorMessage);
    }
}
