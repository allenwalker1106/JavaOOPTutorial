package View;

import Model.Employee;
import Model.Engineer;
import Model.Officer;
import Model.Worker;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;


public class ManagerView {
    private Scanner scanner;

    public ManagerView(){
    }

    public enum Option{
        INSERT,
        DISPLAY_BY_NAME,
        DISPLAY_ALL,
        EXIT,
        HELP,
        INVALID
    }

    public void displayManual(){
        System.out.println("Please pick an option to execute:");
        System.out.println("1 Insert");
        System.out.println("2 Get employee by Name");
        System.out.println("3 Get all employee");
        System.out.println("4 Help");
        System.out.println("5 Exit");
        System.out.print("Enter your option: ");
    }

    public Option getUserOption(){
        scanner = new Scanner(System.in);
        Option e_option= Option.INVALID;
        do{
            int i_inputOption=getValidNumberInput("","[Err] Option should be in range from 1-5: ",(n)->n>0&&n<=5);

            switch (i_inputOption){
                case 1->e_option = Option.INSERT;
                case 2->e_option = Option.DISPLAY_BY_NAME;
                case 3->e_option = Option.DISPLAY_ALL;
                case 4->e_option = Option.HELP;
                case 5->e_option = Option.EXIT;
            }
        }while(e_option == Option.INVALID);
        
        return e_option;
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
                this.displayInvalidStringInput();
            }

        }
        
        return str_inputString;
    }

    public String getInputOfficerName(){
        return getValidStringInput("Please Enter User Name: ");
    }

    public String getInputGender(){
        return getValidStringInput("Please Enter Gender: ");
    }

    public String getInputAddress(){
        return  getValidStringInput("Please Enter Address: ");
    }

    public String getInputEmployeeWork(){
        return getValidStringInput("Please Enter Employee Work: ");
    }

    public String getInputEngineerMajor(){
        return getValidStringInput("Please Enter Engineer Major: ");
    }

    public int getInputAge(){return getValidNumberInput("Please Enter Age: ","[Err] Age must be bigger than 0",(n)->n>0); }

    public void displayOfficer(ArrayList<Officer> c_officers){
        try {
            c_officers.forEach((v)->System.out.println(v.toString()));
        }catch(NullPointerException e){
            this.displayUserNotFound();
        }
    }

    public void displayAllOfficer(Hashtable<String, ArrayList<Officer>> c_officers){
        System.out.println("Officer List: ");
        if(c_officers.size()!=0)
            c_officers.forEach((k,v)->this.displayOfficer(v));
        else
            System.out.println("List is empty");
    }

    public Officer getOfficerDataInput(){
        Officer o_officer=null;
        Officer.Position e_position = this.getOfficerPosition();
        String str_name = this.getInputOfficerName();
        String str_gender = this.getInputGender();
        String str_address = this.getInputAddress();
        int i_age = getInputAge();
        switch(e_position){
            case EMPLOYEE -> {
                String str_work = this.getInputEmployeeWork();
                o_officer = new Employee(str_name,str_gender,str_address,i_age,e_position,str_work);
            }
            case ENGINEER ->{
                String str_major = this.getInputEngineerMajor();
                o_officer = new Engineer(str_name,str_gender,str_address,i_age,e_position,str_major);
            }
            case WORKER -> {
                int i_rank = getValidNumberInput("Please Enter Worker rank: ","Rank should be in range 1-10: ",(n)->n>0&&n<=10);
                o_officer = new Worker(str_name,str_gender,str_address,i_age,e_position,i_rank);
            }

        }
        return o_officer;
    }

    public Officer.Position getOfficerPosition(){
        scanner = new Scanner(System.in);
        Officer.Position e_position= Officer.Position.INVALID;
        do{
            int i_inputOption =getValidNumberInput("1. Employee\t2. Engineer\t3. Worker:","Option should be in range from 1-3: ",(n)->n>0&&n<=3);

            switch (i_inputOption){
                case 1-> e_position = Officer.Position.EMPLOYEE;
                case 2->e_position = Officer.Position.ENGINEER;
                case 3->e_position = Officer.Position.WORKER;
                default->{
                    System.out.println("[Err] Your input is incorrect format.");
                    System.out.println("[Err] Please enter number range from 1-3");
                }
            }
        }while(e_position == Officer.Position.INVALID);
        
        return e_position;
    }


    public void displayUserNotFound(){
        System.out.println("User Not Found:");
    }

    public void displayInvalidStringInput(){
        System.out.println("Invalid String Input");
    }

    public void displayCloseScreen(){
        System.out.println("Closing the Program");
        System.out.println("Thanks you!");
    }

    public void displayHelp(){
        System.out.println("....Help...");
    }
}
