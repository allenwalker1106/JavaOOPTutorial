package com.manager.StudentManager.Exception;

public class UserExistException extends Exception{
    public UserExistException(){
        super("User Already Exist !");
    }
    public UserExistException(String errorMessage) {
        super(errorMessage);
    }
}
