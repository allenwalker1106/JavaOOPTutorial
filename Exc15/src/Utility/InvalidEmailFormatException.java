package Utility;

public class InvalidEmailFormatException extends Exception{
    public InvalidEmailFormatException(){
        super("Email must not contain special character except '.' and use format example@domain.com");
    }
}
