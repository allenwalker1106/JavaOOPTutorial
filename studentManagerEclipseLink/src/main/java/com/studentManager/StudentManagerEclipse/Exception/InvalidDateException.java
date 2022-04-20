package com.studentManager.StudentManagerEclipse.Exception;

public class InvalidDateException extends Exception{
    public InvalidDateException() {
        super("Invalid Date or Date format mismatch. Date must follow format DD MM YYYY (Delimiter can be [ -/]): ");
    }
}
