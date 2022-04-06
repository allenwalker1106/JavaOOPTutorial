package View;

import Model.*;
import Utility.InvalidDateException;
import Utility.InvalidEmailFormatException;
import Utility.InvalidNameException;
import Utility.InvalidPhoneNumberException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentManagerView {
    private Scanner scanner;

    public enum Option{
        INSERT,
        EDIT,
        DELETE,
        GET_BY_ID,
        GET_ID_LIST,
        EXIT,
        HELP,
        INVALID
    }

    public void displayManual(){
        System.out.println("Please pick an option to execute:");
        System.out.println("1 Insert Student");
        System.out.println("3 Delete Student");
        System.out.println("3 Get Student By ID");
        System.out.println("3 Get Students");
        System.out.println("4 Insert Student Result");
        System.out.println("5 Delete Student Result");
        System.out.println("6 Is Regular Student");
        System.out.print("Enter your option: ");
    }

    public void displayCloseScreen(){
        System.out.println("Closing the Program");
        System.out.println("Thanks you!");
    }
}
