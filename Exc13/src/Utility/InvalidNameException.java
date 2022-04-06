package Utility;

public class InvalidNameException extends Exception{
    public InvalidNameException() {
        super("Name must not contain Number or Symbol :");
    }
}
