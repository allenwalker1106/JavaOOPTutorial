package com.manager.StudentManager.Exception;

public class EmailExistException extends Exception{
    public EmailExistException(String errorMessage) {
        super(errorMessage);
    }
}
