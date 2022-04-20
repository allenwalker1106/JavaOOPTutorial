package com.manager.StudentManager.Exception;

public class InvalidNameException extends Exception{
    public InvalidNameException() {
        super("Name must not contain Number or Symbol :");
    }
}
