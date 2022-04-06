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

public class EmployeeManagerView {
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

    public Employee getInputEmployeeData(){
        Employee o_employee=null;
        Employee.Position e_position = this.getInputEmployeePosition();
        String str_id = this.getInputId();
        String str_fullName = this.getInputName();
        Date o_birthday = this.getInputDate();
        String str_phoneNumber = this.getInputPhoneNumber();
        String str_email = this.getInputEmail();
        int i_numberCertificate = this.getInputNumberCertificate();
        ArrayList<Certificate> c_certificates = new ArrayList<>();
        for(int index =0 ;index<i_numberCertificate;index++){
            String str_certificateID = this.getInputCertificateID();
            String str_certificateName = this.getInputCertificateName();
            int i_certificateRank = this.getInputCertificateRank();
            Date o_certificateDate = this.getInputDate();
            c_certificates.add(new Certificate(str_certificateID,str_certificateName,i_certificateRank,o_certificateDate));
        }
        switch(e_position){
            case EXPERIENCE -> {
                int i_experienceYear = this.getInputExperienceYear();
                String str_major = this.getInputMajor();
                o_employee = new Experience(str_id,str_fullName,o_birthday,str_phoneNumber,str_email,e_position,c_certificates,i_experienceYear,str_major);
            }
            case FRESHER ->{
                Date o_graduationDate = this.getInputDate();
                int i_graduateRank = this.getInputGraduateRank();
                String str_graduateSchool = this.getInputGraduateSchool();
                o_employee = new Fresher(str_id,str_fullName,o_birthday,str_phoneNumber,str_email,e_position,c_certificates,o_graduationDate,i_graduateRank,str_graduateSchool);
            }
            case INTERN -> {
                String str_major = this.getInputMajor();
                int i_semester = this.getInputSemester();
                String str_universityName = this.getInputUniversityName();
                o_employee = new Intern(str_id,str_fullName,o_birthday,str_phoneNumber,str_email,e_position,c_certificates,str_major,i_semester,str_universityName);
            }

        }
        return o_employee;
    }

    public String getInputUniversityName(){
        return this.getValidStringInput("Please Enter School Name: ");
    }

    public int getInputSemester(){
        return this.getValidNumberInput("Please Enter Semester: ","[Err] Semester must be bigger than 0",(n)->n>0);
    }

    public String getInputGraduateSchool(){
        return this.getValidStringInput("Please Enter Graduate School : ");
    }

    public int getInputGraduateRank(){
        return this.getValidNumberInput("Please Enter Rank of Graduate: ","[Err] Rank of Graduate must be bigger than 0",(n)->n>0);
    }

    public String getInputMajor(){
        return this.getValidStringInput("Please Enter Major : ");
    }

    public int getInputExperienceYear(){
        return this.getValidNumberInput("Please Enter Experience Year: ","[Err] Experience Year can't be negative",(n)->n>=0);
    }

    public int getInputCertificateRank(){
        return this.getValidNumberInput("Please Enter Rank of certificate: ","[Err] Rank of certificate must be bigger than 0",(n)->n>0);
    }

    public String getInputCertificateID(){
        return this.getValidStringInput("Please Enter Certificate ID : ");
    }

    public String getInputCertificateName(){
        return this.getValidStringInput("Please Enter Certificate Name : ");
    }

    public int getInputNumberCertificate(){
        return this.getValidNumberInput("Please Enter Number of certificate: ","[Err] Number of certificate must be bigger than 0",(n)->n>0);
    }

    public String getInputEmail(){
        String str_email=null;
        boolean b_valid = false;
        do{
            str_email = getValidStringInput("Please Enter Email : ");
            try{
                b_valid=checkEmailValid(str_email);
            }catch(InvalidEmailFormatException e){
                System.err.println(e);
            }catch(NullPointerException e){
                System.err.println(e);
            }
        }while(!b_valid);
        return str_email;
    }

    public boolean checkEmailValid(String str_email) throws InvalidEmailFormatException {
        String str_pattern ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if(this.checkStringValidPattern(str_email,str_pattern)) {
            return true;
        }
        throw  new InvalidEmailFormatException();
    }

    public String getInputPhoneNumber(){
        String str_phoneNumber=null;
        boolean b_valid = false;
        do{
            str_phoneNumber = getValidStringInput("Please Enter Phone Number : ");
            try{
                b_valid=checkPhoneNumberValid(str_phoneNumber);
            }catch(InvalidPhoneNumberException e){
                System.err.println(e);
            }catch(NullPointerException e){
                System.err.println(e);
            }
        }while(!b_valid);
        return str_phoneNumber;
    }

    public boolean checkPhoneNumberValid(String str_phoneNumber) throws InvalidPhoneNumberException {
        String str_pattern="\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})";
        if(this.checkStringValidPattern(str_phoneNumber,str_pattern)) {
            return true;
        }
        throw  new InvalidPhoneNumberException();
    }

    public Date getInputDate(){
        String str_date=null;
        boolean b_valid = false;
        Date o_date= null;
        do{
            str_date = getValidStringInput("Please Enter Date: ");
            try{
                b_valid=checkDateValid(str_date);
            }catch(InvalidDateException e){
                System.err.println(e);
            }catch(NullPointerException e){
                System.err.println(e);
            }
        }while(!b_valid);
        String str_formatDate = formatDate(str_date);
        try{
            o_date= new SimpleDateFormat("dd/MM/yyyy").parse(str_formatDate);
        }catch(ParseException e){
            System.err.println(e);
        }
        return o_date;
    }

    public boolean checkDateValid(String str_date) throws InvalidDateException {
        String str_pattern = "^(0[1-9]|[1-2][0-9])[ \\/-](02)[ \\/-](\\d{4})$|^(0[1-9]|[1-2][0-9]|30)[ \\/-](0[469]|11)[ \\/-](\\d{4})$|^(0[1-9]|[1-2][0-9]|30|31)[ \\/-](0[13578]|10|12)[ \\/-](\\d{4})$";
        if(this.checkStringValidPattern(str_date,str_pattern)) {
            return true;
        }
        throw  new InvalidDateException();
    }

    String formatDate(String str_date){
        String str_formatDate = "";
        String str_pattern = "^(\\d{2})[-\\/ ](\\d{2})[-\\/ ](\\d{4})$";
        Pattern o_pattern = Pattern.compile(str_pattern);
        Matcher o_matcher = o_pattern.matcher(str_date);
        if(o_matcher.find())
        {
            try {
                str_formatDate += o_matcher.group(1) + "/" + o_matcher.group(2) + "/" + o_matcher.group(3);
            }catch(IndexOutOfBoundsException e){
                System.err.println(e);
            }
        }
        return str_formatDate;
    }

    public String getInputName(){
        String str_name=null;
        boolean b_valid = false;
        do{
            str_name = getValidStringInput("Please Enter Full Name: ");
            try{
                b_valid=checkNameValid(str_name);
            }catch(InvalidNameException e){
                System.err.println(e);
            }catch(NullPointerException e){
                System.err.println(e);
            }
        }while(!b_valid);
        return str_name;
    }

    public boolean checkNameValid(String str_name) throws InvalidNameException {
        String str_pattern = "[^a-zA-Z\\s]";
        if(this.checkStringValidPattern(str_name,str_pattern)) {
            throw  new InvalidNameException();
        }
        return true;
    }

    public String getInputId(){
        return getValidStringInput("Please Enter User ID: ");
    }

    public Employee.Position getInputEmployeePosition(){
        scanner = new Scanner(System.in);
        Employee.Position e_position= Employee.Position.INVALID;
        do{
            int i_inputOption =getValidNumberInput("1. Experience\t2. Fresher\t3. Intern:","Option should be in range from 1-3: ",(n)->n>0&&n<=3);

            switch (i_inputOption){
                case 1-> e_position = Employee.Position.EXPERIENCE;
                case 2->e_position = Employee.Position.FRESHER;
                case 3->e_position = Employee.Position.INTERN;
                default->{
                    System.out.println("[Err] Your input is incorrect format.");
                    System.out.println("[Err] Please enter number range from 1-3");
                }
            }
        }while(e_position == Employee.Position.INVALID);

        return e_position;
    }

    public Option getInputUserOption(){
        scanner = new Scanner(System.in);
        Option e_option= Option.INVALID;
        do{
            int i_inputOption=getValidNumberInput("","[Err] Option should be in range from 1-5: ",(n)->n>0&&n<=6);

            switch (i_inputOption){
                case 1->e_option = Option.INSERT;
                case 2->e_option = Option.EDIT;
                case 3->e_option = Option.DELETE;
                case 4->e_option = Option.GET_BY_ID;
                case 5->e_option = Option.GET_ID_LIST;
                case 6->e_option = Option.EXIT;
            }
        }while(e_option == Option.INVALID);

        return e_option;
    }

    public boolean checkStringValidPattern(String str_line,String str_pattern){
        Pattern pattern = Pattern.compile(str_pattern);
        Matcher matcher = pattern.matcher(str_line);
        return matcher.find();
    }

    public String getValidStringInput(String str_notification){
        scanner = new Scanner(System.in);
        boolean b_validInput =false;
        String str_inputString="";
        while(!b_validInput){
            try{
                System.out.println(str_notification);
                str_inputString = scanner.nextLine();
                if(!str_inputString.trim().equals("")){
                    b_validInput=true;
                }
            }catch(NullPointerException e){
                System.err.println("[Err] "+"Null String passed");
            }
        }
        return str_inputString;
    }

    public void displayEmployee(Employee o_employee){
        try{
            System.out.println(o_employee.toString());
        }catch(NullPointerException e){
            System.err.println("Employee Not found");
        }
    }

    public int getValidNumberInput(String str_notification,String str_outRangeNotification, Predicate<Integer> fnc_check){
        scanner = new Scanner(System.in);
        int i_num=0;
        boolean b_validInput = false;
        do{
            try{
                System.out.println(str_notification);
                i_num =scanner.nextInt();
                if(fnc_check.test(i_num)){
                    b_validInput=true;
                }else{
                    System.out.println(str_outRangeNotification);
                }
            }catch(InputMismatchException e){
                System.out.println("[Err] Invalid Input data type");
                System.out.println("[Err] Input must be a number ");
                scanner.nextLine();
            }
        }while(!b_validInput);

        return i_num;
    }

    public void displayEmployeeList(Employee.Position e_type, HashSet<String> c_ids){
        try{
            System.out.println(e_type);
            c_ids.forEach(i -> System.out.print(i+" "));
        }catch(NullPointerException e){
            System.out.println("Employee Department not found :");
        }
    }

    public void displayManual(){
        System.out.println("Please pick an option to execute:");
        System.out.println("1 Insert Employee");
        System.out.println("2 Edit Employee");
        System.out.println("3 Delete Employee");
        System.out.println("4 Get Employee By Id");
        System.out.println("5 Get Employee List ");
        System.out.println("6 Exit");
        System.out.print("Enter your option: ");
    }

    public void displayCloseScreen(){
        System.out.println("Closing the Program");
        System.out.println("Thanks you!");
    }
}
