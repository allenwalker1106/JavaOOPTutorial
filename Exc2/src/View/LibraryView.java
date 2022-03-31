package View;

import Model.Book;
import Model.Document;
import Model.Magazine;
import Model.NewsPaper;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

public class LibraryView {
    private Scanner scanner;
    public enum Option{
        INSERT,
        DELETE,
        DISPLAY_BY_ID,
        GET_ID_LIST,
        EXIT,
        INVALID
    }

    public void displayManual(){
        System.out.println("Please pick an option to execute:");
        System.out.println("1 Insert");
        System.out.println("2 Delete Document");
        System.out.println("3 Display Document By ID");
        System.out.println("4 Get Document ID By Type");
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
                case 2->e_option = Option.DELETE;
                case 3->e_option = Option.DISPLAY_BY_ID;
                case 4->e_option = Option.GET_ID_LIST;
                case 5->e_option = Option.EXIT;
            }
        }while(e_option == Option.INVALID);

        return e_option;
    }

    public Document getDocumentDataInput(){
        Document o_document=null;
        Document.DocumentType e_type = this.getInputDocumentType();
        String str_id = this.getInputDocumentID();
        String str_publisher = this.getInputPublisher();
        int i_publishCount = this.getInputPublishCount();
        switch(e_type){
            case BOOK -> {
                String str_authorName= this.getInputAuthorName();
                int i_totalPageNumber= this.getTotalPageNumber();
                o_document = new Book(str_id,str_publisher,i_publishCount,e_type,str_authorName,i_totalPageNumber);
            }
            case MAGAZINE ->{
                int i_publishNumber= this.getPublishNumber();
                int i_publishMonth= this.getPublishMonth();
                o_document = new Magazine(str_id,str_publisher,i_publishCount,e_type,i_publishNumber,i_publishMonth);
            }
            case NEWSPAPER -> {
                int i_publishDate = this.getPublishDate();
                o_document = new NewsPaper(str_id,str_publisher,i_publishCount,e_type,i_publishDate);
            }

        }
        return o_document;
    }

    public String getInputDocumentID(){
        return getValidStringInput("Please Enter Document ID: ");
    }

    public String getInputPublisher(){
        return getValidStringInput("Please Enter Publisher: ");
    }

    public int getInputPublishCount(){return getValidNumberInput("Please Enter Publish Count: ","[Err] Publish Count must be bigger than 0",(n)->n>0); }

    public String getInputAuthorName(){
        return getValidStringInput("Please Enter Author Name: ");
    }

    public int getTotalPageNumber(){return getValidNumberInput("Please Enter Total Page Number: ","[Err] Page Number must be bigger than 0",(n)->n>0); }

    public int getPublishNumber(){return getValidNumberInput("Please Enter Publish Number: ","[Err] Publish Number must be bigger than 0",(n)->n>0); }

    public int getPublishMonth(){return getValidNumberInput("Please Enter Total Publish Month: ","[Err] Publish Month must be bigger than 0",(n)->n>0); }

    public int getPublishDate(){return getValidNumberInput("Please Enter Total Publish Date: ","[Err] Publish Date must be bigger than 0",(n)->n>0); }


    public Document.DocumentType getInputDocumentType(){
        scanner = new Scanner(System.in);
        Document.DocumentType  e_position= Document.DocumentType.INVALID;
        do{
            int i_inputOption =getValidNumberInput("1. Book\t2. Magazine\t3. NewsPaper:","Option should be in range from 1-3: ",(n)->n>0&&n<=3);

            switch (i_inputOption){
                case 1-> e_position = Document.DocumentType.BOOK;
                case 2->e_position  = Document.DocumentType.MAGAZINE;
                case 3->e_position  = Document.DocumentType.NEWSPAPER;
                default->{
                    System.out.println("[Err] Your input is incorrect format.");
                    System.out.println("[Err] Please enter number range from 1-3");
                }
            }
        }while(e_position == Document.DocumentType.INVALID);

        return e_position;
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
                System.out.println("Invalid String Input");
            }

        }

        return str_inputString;
    }

    public void displayLIstID(Document.DocumentType e_type, Set<String> c_id){
        System.out.println("List ID of "+e_type+": ");
        c_id.forEach((v)->System.out.println(v.toString()));
    }

    public void displayDocument(Document o_document){
        try {
            System.out.println(o_document.toString());
        }catch(NullPointerException e){
            this.displayDocumentNotFound();
        }
    }
    public void displayDocumentNotFound(){
        System.out.println("Document Not Found:");
    }

    public void displayCloseScreen(){
        System.out.println("Closing the Program");
        System.out.println("Thanks you!");
    }
}
